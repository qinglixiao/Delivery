package com.std.network.dns;

import android.text.TextUtils;
import android.util.Log;

import com.std.network.request.NetCallBack;
import com.std.network.request.Result;
import com.std.network.request.STRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:通过DnsPod服务获取ip
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-27.
 */
public class STDns implements Dns {
    private CopyOnWriteArrayList<String> mHostList;

    public STDns(List<String> hostList) {
        mHostList = new CopyOnWriteArrayList<>();
        if(hostList != null) {
            mHostList.addAll(hostList);
        }
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (TextUtils.isEmpty(hostname)) {
            throw new UnknownHostException("host == null");
        } else {
            if (mHostList != null && mHostList.contains(hostname)) {
                try {
                    String ipAddr = DnsManager.getInstance().getIpStrForHost(hostname);
                    if (!TextUtils.isEmpty(ipAddr)) {
                        List<InetAddress> addresses = new ArrayList<>();
                        addresses.add(InetAddress.getByName(ipAddr));
                        Log.d("httpdns", "HttpDns hint, host:" + hostname + "  ip:" + ipAddr);
                        return addresses;
                    }
                } catch (Exception ignored) {
                }
            }
            return SYSTEM.lookup(hostname);
        }
    }

    interface UpdateDnsInfoCallBack {
        void onFail(Exception e);

        void onSuccess(DnsInfo info);
    }

    /**
     * 通过DnsPod服务获取ip
     */
    static class DnsPodService {
        private final static String URL = "http://119.29.29.29/d?dn=%s&ttl=1";

        //失败时间间隔
        private final static int REQUEST_SEPARATE = 1000 * 60 * 5;

        public ConcurrentHashMap<String, Long> mRequestTimeMap = new ConcurrentHashMap<>();

        private OkHttpClient mOkHttpClient = new OkHttpClient();

        //最小ttl,减少httpdns请求次数
        private final static int MIN_TTL = 60 * 15;

        /**
         * 获取查询httpdns的url
         *
         * @param host 查询的host
         * @return url
         */
        private String getRequestUrl(String host) {
            return String.format(URL, host);
        }


        public void updateDnsInfo(final String host, final UpdateDnsInfoCallBack callBack) {
            long currentTime = System.currentTimeMillis();
            Long lastTime = mRequestTimeMap.get(host);
            //为了避免频繁请求,如果当前时间距上次请求时间小于时间间隔,不发请求
            if (lastTime != null && currentTime < lastTime + REQUEST_SEPARATE) {
                return;
            }

            mRequestTimeMap.put(host, currentTime);

            String url = getRequestUrl(host);
            STRequest request = new STRequest.Builder()
                    .url(url)
                    .build();
            request.get(new NetCallBack<String>() {
                @Override
                public void onResult(Result<String> result, Error error) {
                    DnsInfo dnsInfo = parseResponseIP(host, result.getData());
                    if (dnsInfo != null) {
                        if (callBack != null) {
                            callBack.onSuccess(dnsInfo);
                        }
                        //访问成功,上次请求时间设置为0
                        mRequestTimeMap.put(host, 0L);
                    }

                }
            }, String.class);

        }

        /**
         * 解析httpdns返回结果,结果为字符串,例如"222.222.222.222;111.111.111.111,666"
         *
         * @param host 所查询的host
         * @param str  httpdns返回的结果
         * @return DnsInfo
         */
        private DnsInfo parseResponseIP(String host, String str) {
            if (!TextUtils.isEmpty(str)) {
                String[] strings = str.split(",");
                if (strings.length != 2) {
                    Log.e("httpdns", "result is invalid");
                    return null;
                }

                //ip地址str
                String ipListStr = strings[0];

                //生命周期str
                String ttlStr = strings[1];

                //ip list
                String[] ipStrings = ipListStr.split(";");
                int ipNum = ipStrings.length;

                //对于多个ip,考虑服务端负载均衡,随机选择一个
                Random random = new Random();
                String ipString = ipStrings[random.nextInt(ipNum) % ipNum];

                //将ip分解成四个0~255的部分,便于以后效验
                String[] ipParts = ipString.split("\\.");

                //生成DnsInfo
                DnsInfo dnsInfo = new DnsInfo();
                dnsInfo.timestamp = System.currentTimeMillis();
                dnsInfo.host = host;
                try {
                    dnsInfo.ttl = Integer.valueOf(ttlStr);
                    dnsInfo.ipAddr = new int[4];
                    for (int i = 0; i < 4; i++) {
                        dnsInfo.ipAddr[i] = Integer.valueOf(ipParts[i]);
                    }
                    return dnsInfo;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("httpdns", "error result:" + str);
                }

            }

            return null;
        }
    }

    static class DnsManager {
        private Map<String, DnsInfo> mDnsMap;

        DnsPodService mDnsService;

        private static DnsManager sDnsManager;

        public static DnsManager getInstance() {
            if (sDnsManager == null) {
                sDnsManager = new DnsManager();
                sDnsManager.mDnsMap = new ConcurrentHashMap<>();
                sDnsManager.mDnsService = new DnsPodService();
            }
            return sDnsManager;
        }

        /**
         * 获取host的ip地址
         *
         * @param host host
         * @return ip str
         */
        public String getIpStrForHost(final String host) {
            if (!TextUtils.isEmpty(host)) {
                DnsInfo dnsInfo = mDnsMap.get(host);
                if (dnsInfo != null && dnsInfo.isIpValid()) {
                    if (dnsInfo.isExpired()) {
                        //对于ttl过期的host,异步更新,但是继续返回dns info
                        updateDnsInfo(host);
                    }
                    //如果可以获得合法的dns info,返回ip str
                    return dnsInfo.getIpStr();
                }
            }
            //没哟合法的dns info, 异步更新, 返回 null
            updateDnsInfo(host);
            return null;
        }


        /**
         * 异步更新dns info
         *
         * @param host host
         */
        private void updateDnsInfo(final String host) {
            //无法获得合法的dns info, 异步更新host对应的cache
            mDnsService.updateDnsInfo(host, new UpdateDnsInfoCallBack() {
                @Override
                public void onFail(Exception e) {

                }

                @Override
                public void onSuccess(DnsInfo info) {
                    mDnsMap.put(host, info);
                }
            });
        }

        public void preloadHost(List<String> hosts) {
            if (hosts != null && !hosts.isEmpty()) {
                for (String host : hosts) {
                    updateDnsInfo(host);
                }
            }
        }
    }
}
