package com.std.network.dns;

import android.text.TextUtils;
import android.util.Log;

import com.std.network.request.STRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Dns;

/**
 * Description:通过DnsPod服务获取ip
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-27.
 */
public class STDns implements Dns {
    private static Map<String, String> PREDICTION;

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (TextUtils.isEmpty(hostname)) {
            throw new UnknownHostException("host == null");
        } else {
//            if (mHostList != null && mHostList.contains(hostname)) {
//                try {
//                    String ipAddr = DnsManager.getInstance().getIpStrForHost(hostname);
//                    if (!TextUtils.isEmpty(ipAddr)) {
//                        List<InetAddress> addresses = new ArrayList<>();
//                        addresses.add(InetAddress.getByName(ipAddr));
//                        Log.d("httpdns", "HttpDns hint, host:" + hostname + "  ip:" + ipAddr);
//                        return addresses;
//                    }
//                } catch (Exception ignored) {
//                }
//            }
            return SYSTEM.lookup(hostname);
        }
    }

    /**
     * 通过DnsPod服务获取ip
     */
    static class DnsPodService {
        private final static String URL = "http://119.29.29.29/d?dn=%s&ttl=1";

        public void update() {
//            STRequest request = new STRequest();
//            request.get()
        }


    }
}
