package com.std.network.dns;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

public class HttpDnsHelper {
    private static OkHttpClient sOkHttpClient;

    public static OkHttpClient getHttpDnsClient() {
        if (sOkHttpClient == null) {
            CookieStore cookieStore = new STCookieStore();
            CookieManager manager = new CookieManager( cookieStore, CookiePolicy.ACCEPT_ALL );
            CookieHandler.setDefault(manager);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cookieJar(new STCookieJar());
            sOkHttpClient = builder
                    .hostnameVerifier(new HostnameVerifier() {

                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .dns(new STDns())
                    .pingInterval(5, TimeUnit.SECONDS)
                    .build();
        }
        return sOkHttpClient;
    }
}
