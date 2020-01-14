package com.std.network.client;

import com.std.network.dns.STCookieJar;
import com.std.network.dns.STDns;

import okhttp3.CookieJar;
import okhttp3.Dns;
import okhttp3.OkHttpClient;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-30.
 */
public abstract class ClientConfig {

    CookieJar getCookieJar() {
        return new STCookieJar();
    }

    Dns getDns() {
        return new STDns();
    }

    void attach(OkHttpClient.Builder builder) {
        CookieJar cookieJar = getCookieJar();
        Dns dns = getDns();
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }
        if (dns != null) {
            builder.dns(dns);
        }
    }

}
