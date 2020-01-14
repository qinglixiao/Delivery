package com.std.network.client;

import com.std.network.dns.STCookieStore;
import com.std.network.dns.STSSLSocketManager;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.concurrent.TimeUnit;

import me.std.common.utils.AppContextUtil;
import me.std.common.utils.FileUtil;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-30.
 */
public class STHttpClient {
    private static final int TIMEOUT = 60;
    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    private OkHttpClient.Builder okClientBuilder;

    private STHttpClient(ClientConfig config) {
        okClientBuilder = new OkHttpClient.Builder();
        defaultConfig();
        if (config != null) {
            config.attach(okClientBuilder);
        }
    }

    private void defaultConfig() {
        CookieStore cookieStore = new STCookieStore(AppContextUtil.getAppContext());
        CookieManager manager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);

        okClientBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(FileUtil.getOkHttpCacheFile(), CACHE_SIZE))
                .sslSocketFactory(STSSLSocketManager.getSSLSocketFactory(), STSSLSocketManager.getX509TrustManager());
    }

    public OkHttpClient get() {
        return okClientBuilder.build();
    }

    public static class Builder {
        ClientConfig config;

        public Builder addConfig(ClientConfig config) {
            this.config = config;
            return this;
        }

        public STHttpClient build() {
            return new STHttpClient(config);
        }
    }
}
