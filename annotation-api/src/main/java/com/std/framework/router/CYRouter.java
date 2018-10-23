package com.std.framework.router;

import android.app.Application;
import android.content.Context;

import com.std.framework.router.exceptions.CYRouterException;

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
    private static Context context;
    private static boolean isInit = false;

    public static void init(Context application) {
        if (isInit) {
            return;
        }
        if (!(application instanceof Application)) {
            throw new IllegalStateException("CYRouter context is not application!!!");
        }
        context = application;
        isInit = true;
    }

    public static <T> CPromise<T> open(String url) {
        Promise promise = new Promise(new Ask(url));
        promise.setContext(context);
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> open(String url, String paramJson) {
        Promise promise = new Promise(new Ask(url, paramJson));
        promise.setContext(context);
        return new CPromise(promise);
    }

    //params->(String key,Object value)
    public static <T> CPromise<T> open(String url, Object... params) {
        Promise promise = new Promise(new Ask(url, params));
        promise.setContext(context);
        return new CPromise(promise);
    }

    public static <T> CPromise<T> open(String url, Map<String, Object> paramMap) {
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

    public static <T> CPromise<T> open(String schema, String host, String path, Map<String, Object> paramMap) {
        Promise promise = new Promise(new Ask(schema, host, path, paramMap));
        promise.setContext(context);
        return new CPromise<>(promise);
    }

}
