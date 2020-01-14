package com.std.network.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-30.
 */
public class STClientConfig extends ClientConfig {

    @Override
    void attach(OkHttpClient.Builder builder) {
        super.attach(builder);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }
}
