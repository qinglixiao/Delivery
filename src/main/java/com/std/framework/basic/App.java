package com.std.framework.basic;

import android.app.Application;
import android.util.Log;

import com.library.util.LibUtil;
import com.library.util.LogUtil;
import com.std.framework.comm.clazz.STDActivityManager;
import com.std.framework.comm.clazz.STDUncaughtExceptionHandler;

public class App extends Application {
	/**全局应用实例*/
	public static App instance;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		configLogSystem();
		setExceptionHandle();
	}

	private void setExceptionHandle() {
		Thread.setDefaultUncaughtExceptionHandler(new STDUncaughtExceptionHandler());
	}

	/**
	 * 
	 * 描          述 ：配置日志系统
	 * 创建日期  : 2014-7-14
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 *
	 */
	private void configLogSystem() {
		if (LibUtil.isExternalStorageAvailable())
			LogUtil.configureOnlyLogFile(LibUtil.getAppDirectory(this));
		else
			Log.e("APP", "日志系统配置失败---[设备无存储卡]");
	}
	
	/**
	 * 
	 * 描          述 ：退出系统
	 * 创建日期  : 2014-7-14
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 *
	 */
	public void exit(){
		STDActivityManager.getInstance().finishAll();
	}
	
}
