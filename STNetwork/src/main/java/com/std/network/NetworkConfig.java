package com.std.network;

import com.std.network.client.STClientConfig;
import com.std.network.client.STHttpClient;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-27.
 */
public class NetworkConfig {
    public static final String APPID = "vtTDl6cxKNGoevpxKMCq";
    public static final String APPKEY = "0n0i32lXlRkHOP88aSaWAvmaEqVPgibB";

    public static STHttpClient httpClient;

    public static String getDomain() {
        return "https://www.oschina.net";
    }

    public static String getUserToken() {
        return "aebf1421-3191-4b3b-b7b9-8fa61e2def8f";
    }

    public static String getUserAgent() {
        return "platform:android,deviceId:FFK0217705003490,appVersion:1.8.0,platformVersion:24,toonType:102";
    }

    public static void initNetClient() {
        httpClient = new STHttpClient.Builder()
                .addConfig(new STClientConfig())
                .build();
    }
}
