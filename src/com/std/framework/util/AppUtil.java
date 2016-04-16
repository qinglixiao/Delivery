package com.std.framework.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.std.framework.activity.App;

public class AppUtil {
    // 返回
    public static void onKeyBackPressed(Activity activity) {
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }

    /**
     * 取消或者删除所有状态栏通知
     *
     * @param context
     */
    public static void cancelAllNotification(Context context) {
        ((NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
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

}
