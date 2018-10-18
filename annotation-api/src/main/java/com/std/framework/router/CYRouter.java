package com.std.framework.router;

import android.app.Application;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYRouter {
    private static Application context;
    private static boolean isInit = false;

    public static void init(Application application) {
        context = application;
        isInit = true;
    }

    public static <T> CPromise<T> open(String url) {
        Promise promise = new Promise(new Ask(url));
        promise.setContext(context);
        return new CPromise(promise);
    }

    public static <T> CPromise<T> open(String url, String paramJson) {
        Promise promise = new Promise(new Ask(url, paramJson));
        promise.setContext(context);
        return new CPromise(promise);
    }

    public static <T> CPromise<T> open(String url, Map<String,Object> paramMap) {
        Promise promise = new Promise(new Ask(url, paramMap));
        promise.setContext(context);
        return new CPromise(promise);
    }

    public static <T> CPromise<T> open(String schema, String host, String path) {
        Promise promise = new Promise(new Ask(schema, host, path));
        promise.setContext(context);
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> open(String schema, String host, String path, String paramJson) {
        Promise promise = new Promise(new Ask(schema, host, path, paramJson));
        promise.setContext(context);
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> open(String schema, String host, String path, List paramList) {
        Promise promise = new Promise(new Ask(schema, host, path, paramList));
        promise.setContext(context);
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> open(String schema, String host, String path, Map<String,Object> paramMap) {
        Promise promise = new Promise(new Ask(schema, host, path, paramMap));
        promise.setContext(context);
        return new CPromise<>(promise);
    }


}
