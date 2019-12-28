package me.std.common.utils;

import android.os.Build;

/**
 * 系统版本信息
 */
public class About {
    private static int API_INT = Build.VERSION.SDK_INT;

    //Android 5.0
    public static boolean beforeAndroid50() {
        return API_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    //Android Q
    public static boolean beforeAndroidQ() {
        return API_INT < Build.VERSION_CODES.Q;
    }

}
