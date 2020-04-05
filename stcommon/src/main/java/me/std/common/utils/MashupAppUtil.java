//
//  MashupAppUtils.java
//  SpringRainDoctor
//
//  Created by Eden He on 2011-11-13
//  Copyright (c) 2011 Chunyu.mobi 
//  All rights reserved
//

package me.std.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import me.std.common.R;

public final class MashupAppUtil {

    public static int SMS_REQUEST = 10086;

    public static void openVideo(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = "video/*";
        Uri uri = Uri.parse(url);
        intent.setDataAndType(uri, type);
        activity.startActivity(intent);
    }

    /**
     * @param activity
     * @param token
     */
    public static void takePhoto(Activity activity, int token) {
        takePhoto(activity, token, null);
    }

    public static void takePhoto(Activity activity, int token, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(activity,
                            "me.chunyu.ChunyuDoctor.fileprovider", new File(uri.getPath())));
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        activity.startActivityForResult(intent, token);
    }

    public static void takePhoto(Fragment fragment, int token, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(fragment.getActivity(),
                            "me.chunyu.ChunyuDoctor.fileprovider", new File(uri.getPath())));
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        fragment.startActivityForResult(intent, token);
    }

    /**
     * @param activity
     * @param token
     */
    public static void choosePhoto(Activity activity, int token) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(intent, token);
    }

    public static void choosePhoto(Fragment fragment, int token) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        fragment.startActivityForResult(intent, token);
    }

    /**
     * If take photo success, return the bitmap. Else, return null.
     *
     * @return
     * @throws FileNotFoundException
     */
    public static Bitmap getBitmapFromPhotoTaker(Intent data) {
        if (data != null) {
            Bundle myExtras = data.getExtras();
            if (myExtras != null) {
                return (Bitmap) myExtras.get("data"); // TODO has constant?
            }
        }

        return null;
    }

    public static void viewUri(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        context.startActivity(intent);
    }

    public static void makeCall(Context context, String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void sendSms(Context context, String phoneNumber, String content) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            if (!TextUtils.isEmpty(content)) {
                intent.putExtra("sms_body", content);
            }
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtil.getInstance().showToast(e.getMessage());
        }
    }

    public static void sendSmsForResult(Fragment fragment, String phoneNumber, String content) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            if (!TextUtils.isEmpty(content)) {
                intent.putExtra("sms_body", content);
            }
            fragment.startActivityForResult(intent, SMS_REQUEST);
        } catch (ActivityNotFoundException e) {
            ToastUtil.getInstance().showToast(e.getMessage());
        }
    }

    public static void sendInviteMessage(Context context, String title, String message) {
        Intent inviteIntent = new Intent(Intent.ACTION_SEND);
        inviteIntent.setType("text/plain");
        inviteIntent.putExtra(Intent.EXTRA_TEXT, message);
        inviteIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        context.startActivity(Intent.createChooser(inviteIntent, "选择邀请方式"));
    }

    public static void openAppInMarket(Context context) {
        openAppInMarket(context, context.getPackageName());
    }

    public static void openAppInMarket(Context context, String packageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri u = Uri.parse("market://details?id=" + packageName);
            intent.setData(u);
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.getInstance().showToast("您的手机没有安装应用市场");
        }
    }

    public static boolean isQQInstalled(Context context) {
        return isPkgInstalled(context, "com.tencent.mobileqq");
    }

    public static boolean isQZoneInstalled(Context context) {
        return isPkgInstalled(context, "com.qzone");
    }

    public static boolean isPkgInstalled(Context context, String pkgName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过Uri安装App
     *
     * @param context 进程
     * @param uri     下载Uri
     */
    public static void installApp(Context context, Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件路径安装App
     *
     * @param context  进程
     * @param filePath 文件路径
     */
    public static void installApp(Context context, String filePath) {
        Uri uri = Uri.fromFile(new File(filePath));
        installApp(context, uri);
    }

    /**
     * 获取一个包名对应的versionName
     *
     * @param context
     * @param packageName  app的包名
     * @param defaultValue 出错时默认的版本号
     * @return
     */
    public static String getAppVersionName(Context context, String packageName,
                                           String defaultValue) {
        String value = defaultValue;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            value = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取一个包名对应的versionName，如果出错，返回null
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppVersionName(Context context, String packageName) {
        return getAppVersionName(context, packageName, null);
    }

    /**
     * 获取context所在包名的versionName
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName(), null);
    }

    /**
     * 判断context所属的application的是否是当前的前台页面
     *
     * @param context
     * @return
     */
    public static boolean isApplicationForeground(Context context) {
        context = context.getApplicationContext();
        if (context == null) {
            return false;
        }
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
                activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName.equals(packageName)) {

                return true;
            }
        }
        return false;
    }

    /**
     * 判断由packageName指定包名的application的是否是当前的前台页面
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isApplicationForeground(Context context, String packageName) {
        context = context.getApplicationContext();
        if (context == null) {
            return false;
        }
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
                activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
