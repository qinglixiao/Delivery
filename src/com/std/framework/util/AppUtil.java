package com.std.framework.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.std.framework.App;

public class AppUtil {

	/**
	 * 
	 * 描      述 ：获取应用内存中数据保存文件
	 * 创建日期 ： 2014-5-20
	 * 作      者 ： lx
	 * 修改日期 ： 
	 * 修  改 者 ：
	 * @version： 1.0
	 * @param context
	 * @return
	 *
	 */
	public static SharedPreferences getAppPreferences(Context context) {
		return context.getSharedPreferences("esgapp", Context.MODE_WORLD_READABLE);
	}
	
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
	public static boolean isNetWorkAvailable(){
		ConnectivityManager manager = (ConnectivityManager) App.stdApp.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if(info != null)
			return info.isAvailable();
		else
			return false;
	}
	
	/**
	 * 
	 * 描          述 ：保存布尔值到本地文件
	 * 创建日期  : 2014-10-29
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param key
	 * @param value
	 *
	 */
	public static boolean saveBoolean(Context context, String key,boolean value){
		SharedPreferences preferences = getAppPreferences(context);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}
}
