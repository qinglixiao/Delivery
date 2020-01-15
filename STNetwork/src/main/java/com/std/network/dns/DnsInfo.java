package com.std.network.dns;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020-01-15.
 */
public class DnsInfo {
    public String host;

    public int[] ipAddr = new int[4];

    //生命周期
    public int ttl;

    //获取时的时间戳
    public long timestamp;

    /**
     * 是否过期
     *
     * @return true 已过期; false 未过期
     */
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        return currentTime > timestamp + ttl * 1000;
    }

    /**
     * 获取ip字符串
     *
     * @return ip字符串
     */
    public String getIpStr() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(ipAddr[i]);
            builder.append(".");
        }
        builder.append(ipAddr[3]);
        return builder.toString();
    }

    /**
     * 判断ip地址时候合法,满足以下条件为合法:
     * 由四个0-255的数字组成
     *
     * @return true/false
     */
    public boolean isIpValid() {
        if (ipAddr != null && ipAddr.length == 4) {
            for(int ipPart : ipAddr){
                if(ipPart < 0 || ipPart > 255){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
