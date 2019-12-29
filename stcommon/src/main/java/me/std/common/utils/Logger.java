package me.std.common.utils;

import android.util.Log;

public class Logger {

    private static final String TAG = "Logger";

    public static void d(String msg) {
        if (AppContextUtil.debug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (AppContextUtil.debug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (AppContextUtil.debug) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (AppContextUtil.debug) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (AppContextUtil.debug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (AppContextUtil.debug) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (AppContextUtil.debug) {
            Log.v(tag, msg);
        }
    }

}