package com.std.network.dns;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-12-27.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class STCookieStore implements CookieStore {
    @Override
    public void add(URI uri, HttpCookie cookie) {

    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return null;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return null;
    }

    @Override
    public List<URI> getURIs() {
        return null;
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }
}
