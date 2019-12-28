package com.std.network.dns;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-27.
 */
public class STCookieJar implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
