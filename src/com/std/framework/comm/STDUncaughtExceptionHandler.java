package com.std.framework.comm;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Activity;
import android.content.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class STDUncaughtExceptionHandler extends Exception implements UncaughtExceptionHandler {
	private static final long serialVersionUID = -6094962699800441386L;

	/** 系统默认的UncaughtException处理类*/
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	public STDUncaughtExceptionHandler() {
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		handleException(ex);
		mDefaultHandler.uncaughtException(thread, ex);
	}

	/**
	 * 自定义异常处理：收集错误信息&发送错误报告
	 * @param ex
	 * @return true:处理了该异常信息;否则返回false
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null)
			return false;

		Activity activity = STDActivityManager.getInstance().getCurrent();
		String crashReport = getCrashReport(activity, ex);
		Logger logger = LoggerFactory.getLogger(activity.getComponentName().getShortClassName());
		logger.error(crashReport);
		return true;
	}

	/**
	 * 获取崩溃异常报告
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE + "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception: " + ex.getMessage() + "\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}

}
