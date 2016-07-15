package com.std.framework.util;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.std.framework.basic.App;

public class AppUtil {
    // 返回
    public static void onKeyBackPressed(Activity activity) {
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }

    public static String getMetaData(String metaName) {
        String meta_str = "";
        try {
            PackageManager packageManager = App.instance.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(
                        App.instance.getPackageName(),
                        PackageManager.GET_META_DATA);
                if (applicationInfo != null && applicationInfo.metaData != null && !TextUtils.isEmpty(metaName)) {
                    meta_str = applicationInfo.metaData.getString(metaName);
                }
            }
        } catch (PackageManager.NameNotFoundException ex) {
            ex.getStackTrace();
        }
        return meta_str;
    }

    /**
     * 获取app最多允许占用的内存
     * @return
     */
    public static long getMaxMemoryAllocated(){
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获取app当前总共分配的内存
     * @return
     */
    public static long getTotalMemoryAllocated(){
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获取app当前释放的内存数
     * @return
     */
    public static long getFreeMemoryAllocated(){
        return Runtime.getRuntime().freeMemory();
    }


}
