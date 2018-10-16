package com.std.framework.router;

import android.app.Application;

import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.Resolve;

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

    public static <T> CPromise open(String url) {
        Promise promise = new Promise(new Ask(url));
        promise.setContext(context);
        return new CPromise(promise);
    }

    public static <T> void open(String url, Resolve<T> resolve) {
        Promise promise = new Promise(new Ask(url));
        promise.setContext(context);
        promise.call(resolve, null);
    }

    public static <T> void open(String url, Resolve<T> resolve, Capture capture){
        Promise promise = new Promise(new Ask(url));
        promise.setContext(context);
        promise.call(resolve, null);
    }


}
