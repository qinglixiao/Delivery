package com.std.framework.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;

import java.io.File;

public class AppUtil {

    private static Context mContext;

    public static Context getAppContext() {
        if (mContext == null) {
            throw new IllegalStateException("AppUtil 's initApp not called!!!");
        }
        return mContext;
    }

    public static void initApp(Application app) {
        mContext = app.getApplicationContext();
    }

    public boolean isDebug() {
        return (0 != (mContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    /**
     * 描          述 ：获取应用在SD卡的安装目录
     *
     * @return
     * @version : 1.0
     */
    public static String getAppDirectory() {
        String mApplicationName = mContext.getPackageManager().getApplicationLabel(mContext.getApplicationInfo()).toString();
        String dir = Environment.getExternalStoragePublicDirectory(mApplicationName).getPath();
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    /**
     * 获取app内存安装目录下/files目录
     * @return
     */
    public static File getMemoryFileDir(){
        //app内存安装目录/files
        return mContext.getFilesDir();
    }

    /**
     * 描          述 ：获取SD卡根目录
     *
     * @return
     * @version : 1.0
     */
    public static String getSdCardRootDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 描          述 ：获取应用缓存目录(内存)
     *
     * @return
     * @version : 1.0
     */
    public static String getCacheDirectory() {
        return mContext.getCacheDir().getPath();
    }

    /**
     * 描          述 ：获取SD卡上的下载目录
     *
     * @return
     * @version : 1.0
     */
    public static String getDownLoadDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    /**
     * 点击返回按键
     *
     * @param activity
     */
    public static void onKeyBackPressed(Activity activity) {
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        activity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }

    /**
     * 获取meta值
     *
     * @param metaName
     * @return
     */
    public static String getMetaData(String metaName) {
        String meta_value = "";
        try {
            PackageManager packageManager = mContext.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(
                        mContext.getPackageName(),
                        PackageManager.GET_META_DATA);
                if (applicationInfo != null && applicationInfo.metaData != null && !TextUtils.isEmpty(metaName)) {
                    meta_value = applicationInfo.metaData.getString(metaName);
                }
            }
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return meta_value;
    }

    /**
     * 获取app最多允许占用的内存
     *
     * @return
     */
    public static long getMaxMemoryAllocated() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获取app当前总共分配的内存
     *
     * @return
     */
    public static long getTotalMemoryAllocated() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获取app当前释放的内存数
     *
     * @return
     */
    public static long getFreeMemoryAllocated() {
        return Runtime.getRuntime().freeMemory();
    }

}
