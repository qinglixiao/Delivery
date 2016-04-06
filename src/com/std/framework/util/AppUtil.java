package com.std.framework.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.KeyEvent;

import com.std.framework.App;

public class AppUtil {

	/**
	 * 
	 * 描          述 ：判断网络是否可用
	 * 创建日期  : 2014-6-11
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public static boolean isNetWorkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) App.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null)
			return info.isAvailable();
		else
			return false;
	}

	// 返回
	public static void onTitleBackPressed(Activity activity) {
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
