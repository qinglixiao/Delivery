package com.std.framework.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
	
}
