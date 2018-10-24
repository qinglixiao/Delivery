package com.std.framework.router;

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

    public static <T> CPromise<T> build(String url) {
        Promise promise = new Promise(new Ask(url));
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> build(String url, String paramJson) {
        Promise promise = new Promise(new Ask(url, paramJson));
        return new CPromise(promise);
    }

    //params->(String key,Object value)
    public static <T> CPromise<T> build(String url, Object... params) {
        Promise promise = new Promise(new Ask(url, params));
        return new CPromise(promise);
    }

    public static <T> CPromise<T> build(String url, Map<String, Object> paramMap) {
        Promise promise = new Promise(new Ask(url, paramMap));
        return new CPromise(promise);
    }

    public static <T> CPromise<T> build(String schema, String host, String path) {
        Promise promise = new Promise(new Ask(schema, host, path));
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> build(String schema, String host, String path, String paramJson) {
        Promise promise = new Promise(new Ask(schema, host, path, paramJson));
        return new CPromise<>(promise);
    }

    public static <T> CPromise<T> build(String schema, String host, String path, Map<String, Object> paramMap) {
        Promise promise = new Promise(new Ask(schema, host, path, paramMap));
        return new CPromise<>(promise);
    }

}