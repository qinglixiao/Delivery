package com.std.framework.router.utils;

import com.std.framework.router.RouterCache;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RouterHelper {
    private final RouterCache cache = new RouterCache();

    private static class Lazy {
        static RouterHelper instance = new RouterHelper();
    }

    public static RouterHelper getIntance() {
        return Lazy.instance;
    }

    public Object getMirrorClass(String name) {
        return cache.getMirrorObj(name);
    }

    public void addMirrorClass(String name, Object clazz) {
        cache.addMirrorObj(name, clazz);
    }

}
