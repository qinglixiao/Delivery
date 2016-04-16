package com.std.framework.core;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by gfy on 2016/4/10.
 */
public class Logger {
    public static boolean PUT_OUT = true;

    private static int VERBOSE = 1;
    private static int DEBUG = 2;
    private static int INFO = 3;
    private static int WARN = 4;
    private static int ERROR = 5;

    //日志打印级别
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if(PUT_OUT && LEVEL >= VERBOSE && !TextUtils.isEmpty(msg)) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if(PUT_OUT && LEVEL >= DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if(PUT_OUT && LEVEL >= INFO && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if(PUT_OUT && LEVEL >= WARN && !TextUtils.isEmpty(msg)) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(PUT_OUT && LEVEL >= ERROR && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void m(String msg) {
        if(PUT_OUT && LEVEL >= ERROR && !TextUtils.isEmpty(msg)) {
            Log.e("LX", msg);
        }
    }

}
