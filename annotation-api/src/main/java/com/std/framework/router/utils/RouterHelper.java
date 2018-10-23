package com.std.framework.router.utils;

import android.os.Handler;
import android.os.Looper;

import com.std.framework.router.RouterCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RouterHelper {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public static final ExecutorService EXECUTER = Executors.newCachedThreadPool();
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
