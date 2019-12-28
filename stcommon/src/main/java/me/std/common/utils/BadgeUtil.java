package me.std.common.utils;


/**
 * Description:
 * 应用启动图标未读消息数显示 工具类  (效果如：QQ、微信、未读短信 等应用图标)<br/>
 * 依赖于第三方手机厂商(如：小米、三星)的Launcher定制、原生系统不支持该特性<br/>
 * 该工具类 支持的设备有 小米、华为、三星、索尼【其中小米、华为、三星亲测有效、索尼未验证】
 *  资料参考：https://heinika.github.io/2016/12/28/Android/AndroidShortBadge/
 * Author: huangyuan
 * Create on: 2018/9/29
 * Job number: 1800829
 * Phone: 13120112517
 * Email: huangyuan@chunyu.me
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
  * Description:
  * Author: huangyuan
  * Create on: 2018/10/12
  * Job number: 1800829
  * Phone: 13120112517
  * Email: huangyuan@chunyu.me
  */
public class BadgeUtil {

    private static boolean mIsSupportedBade = true;
    //索尼手机
    private static final String SONY_BADGE_SHOW_MESSAGE_EXTRA = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
    private static final String SONY_BADGE_ACTIVITY_NAME_EXTRA = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
    private static final String SONY_BADGE_MESSAGE_EXTRA = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
    private static final String SONY_BADGE_PACKAGE_NAME_EXTRA = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
    private static final String SONY_BADGE_UPDATE_BADGE_ACTION = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";

    //三星手机
    private static final String SAMSUNG_BADGE_COUNT_UPDATE = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String SAMSUNG_BADGE_COUNT_EXTRA = "badge_count";
    private static final String SAMSUNG_BADGE_COUNT_PACKAGE_NAME_EXTRA = "badge_count_package_name";
    private static final String SAMSUNG_BADGE_COUNT_CLASS_NAME_EXTRA = "badge_count_class_name";

    /**
     * Set badge count<br/>
     * Support manufacturers: xiaomi / sony / Samsung / LG / huawei
     *
     * @param context The context of the application package.
     * @param count   Badge number to be set
     */
    public static void setBadgeCount(Notification notification, Context context, int count) {
        if (!mIsSupportedBade) {
            return;
        }
        count = checkCount(count);
        try {
            if (Rom.isMiui()) {
            } else if (Rom.isSony()) {
                mIsSupportedBade = setBadgeOfSony(context, count);
            } else if (Rom.isSumSung() || Rom.isLG()) {
                mIsSupportedBade = setBadgeOfSumsung(context, count);
            } else if (Rom.isEmui()) {
                mIsSupportedBade = sendToHuawei(context, count);
            } else {
                Log.i("badge", "not supported badge!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mIsSupportedBade = false;
        }
    }

    /**
     * Adjust badge number to between [0, 99]
     *
     * @param count   Badge number
     * @return Badge  number to be set
     */
    private static int checkCount(int count) {
        if (count <= 0) {
            count = 0;
        } else {
            count = Math.max(0, Math.min(count, 99));
        }
        return count;
    }


    /**
     * 设置小米手机Badge
     * 文档：https://dev.mi.com/doc/p=3904/index.html
     *
     * @param context   context
     * @param count     count
     * @return Whether  support badge or not
     */
    private static boolean setBadgeOfMIUI(Notification notification, Context context, int count) {
        try {
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, count);
            mNotificationManager.notify(3456, notification);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 设置索尼手机Badge
     * <p/>
     * 需添加权限：<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE" />
     *
     * @param context   context
     * @param count     count
     * @return Whether  support badge or not
     */
    private static boolean setBadgeOfSony(Context context, int count) {
        try {
            String launcherClassName = getLauncherClassName(context);
            if (TextUtils.isEmpty(launcherClassName)) {
                return false;
            }

            boolean isShow = true;
            if (count == 0) {
                isShow = false;
            }
            Intent localIntent = new Intent();
            localIntent.setAction(SONY_BADGE_UPDATE_BADGE_ACTION);
            localIntent.putExtra(SONY_BADGE_SHOW_MESSAGE_EXTRA, isShow);//是否显示
            localIntent.putExtra(SONY_BADGE_ACTIVITY_NAME_EXTRA, launcherClassName);//启动页
            localIntent.putExtra(SONY_BADGE_MESSAGE_EXTRA, String.valueOf(count));//数字
            localIntent.putExtra(SONY_BADGE_PACKAGE_NAME_EXTRA, context.getPackageName());//包名
            context.sendBroadcast(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 设置三星手机Badge
     *
     * @param context   context
     * @param count     count
     * @return Whether  support badge or not
     */
    private static boolean setBadgeOfSumsung(Context context, int count) {
        try {
            String launcherClassName = getLauncherClassName(context);
            if (TextUtils.isEmpty(launcherClassName)) {
                return false;
            }
            Intent intent = new Intent(SAMSUNG_BADGE_COUNT_UPDATE);
            intent.putExtra(SAMSUNG_BADGE_COUNT_EXTRA, count);
            intent.putExtra(SAMSUNG_BADGE_COUNT_PACKAGE_NAME_EXTRA, context.getPackageName());
            intent.putExtra(SAMSUNG_BADGE_COUNT_CLASS_NAME_EXTRA, launcherClassName);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置华为手机badge
     * 文档：http://developer.huawei.com/consumer/cn/wiki/index.php?title=%E5%8D%8E%E4%B8%BA%E6%A1%8C%E9%9D%A2%E8%A7%92%E6%A0%87SDK%E4%B8%8B%E8%BD%BD
     *
     * @param context   context
     * @param count     count
     * @return Whether  support badge or not
     * 需要权限：<uses-permission android:name="com.huawei.android.launcher.permission.CHANGE_BADGE"/>
     */
    private static boolean sendToHuawei(Context context, int count) {
        try {
            String launcherClassName = getLauncherClassName(context);
            if (TextUtils.isEmpty(launcherClassName)) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("package", context.getPackageName());
            bundle.putString("class", launcherClassName);
            bundle.putInt("badgenumber", count);
            ContentResolver t = context.getContentResolver();
            t.call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 重置、清除Badge未读显示数
     */
    public static void resetBadgeCount(Notification notification, Context context) {
        setBadgeCount(notification, context, 0);
    }

    /**
     * Retrieve launcher activity name of the application from the context
     *
     * @param context The context of the application package.
     * @return launcher activity name of this application. From the
     * "android:name" attribute.
     */
    private static String getLauncherClassName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            // To limit the components this Intent will resolve to, by setting an
            // explicit package name.
            intent.setPackage(context.getPackageName());
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // All Application must have 1 Activity at least.
            // Launcher activity must be found!
            ResolveInfo info = packageManager
                    .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

            // get a ResolveInfo containing ACTION_MAIN, CATEGORY_LAUNCHER
            // if there is no Activity which has filtered by CATEGORY_DEFAULT
            if (info == null) {
                info = packageManager.resolveActivity(intent, 0);
            }

            return info.activityInfo.name;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}