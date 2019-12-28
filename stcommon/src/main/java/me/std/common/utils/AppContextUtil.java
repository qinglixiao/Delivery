package me.std.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Description: app上下文工具类
 * Author: lixiao
 * Create on: 2018/7/19.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class AppContextUtil {
    private static Context mContext;

    //debug模式
    public static boolean DEBUG = true;
    //线上环境
    public static boolean ON_LINE = false;

    public static boolean isAppForegroundRunning = true;

    public static void initApp(Context app) {
        mContext = app;
        DEBUG = (0 != (mContext.getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE));
    }

    public static Context getAppContext() {
        if (mContext == null) {
            throw new IllegalStateException("AppContextUtil's initApp not called!!!");
        }
        return mContext;
    }

    /**
     * 强制收起输入法面板
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        if (activity == null) {
            return;
        }

        View curfocus = activity.getCurrentFocus();
        if (null == curfocus) {
            return;
        }

        IBinder windowToken = curfocus.getWindowToken();
        if (null == windowToken) {
            return;
        }

        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制打开输入法面板
     *
     * @param context
     * @param view
     */
    public static void showSoftKeyBoard(Context context, View view) {
        if (context == null) {
            return;
        }
        view.requestFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void sendIntent(Context context, String intentStr) {
        try {
            // 根据packagename packagevcode 取得处理该消息的客户端activity or broadcastReceiver or service 交给它处理
            PackageManager pm = context.getPackageManager();

            // 查询能处理的activity or broadcastreceiver
            Intent msgIntent = Intent.parseUri(intentStr, 0);
            List<ResolveInfo> infos = null;
            if ((infos = pm.queryBroadcastReceivers(msgIntent, 0)) != null && (infos.size() > 0)) {
                addPackageNameIfNeed(context, msgIntent, infos);
                context.sendBroadcast(msgIntent);
            } else if (((infos = pm.queryIntentActivities(msgIntent, 0)) != null) && (infos.size() > 0)) {
                addPackageNameIfNeed(context, msgIntent, infos);
                msgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(msgIntent);
            } else if (((infos = pm.queryIntentServices(msgIntent, 0)) != null) && (infos.size() > 0)) {
                addPackageNameIfNeed(context, msgIntent, infos);
                context.startService(msgIntent);
            } else {
                // 发URL，交给系统处理
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                // 这里URL不应该为空
                urlIntent.setData(Uri.parse(intentStr));
                urlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(urlIntent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果查询出来的ResolveInfo 有框自己的话，强制对Intent增加框的PackageName
     *
     * @param context   Context
     * @param msgIntent 解析出来的Intent
     * @param infos     查询出来的ResolveInfo列表
     */
    private static void addPackageNameIfNeed(Context context, Intent msgIntent, List<ResolveInfo> infos) {
        String packageName = context.getPackageName();
        for (ResolveInfo info : infos) {
            if (null != info.activityInfo && TextUtils.equals(info.activityInfo.packageName, packageName)) {
                msgIntent.setPackage(packageName);
                return;
            }

            if (null != info.serviceInfo && TextUtils.equals(info.serviceInfo.packageName, packageName)) {
                msgIntent.setPackage(packageName);
                return;
            }
        }
    }

    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    public static void setOnLine(boolean online) {
        ON_LINE = online;
    }

    public static void setAppForegroundRunning(boolean isForeground) {
        isAppForegroundRunning = isForeground;
    }

}
