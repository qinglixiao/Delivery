package com.std.framework.comm.clazz;

import com.std.framework.core.Logger;

import java.lang.Thread.UncaughtExceptionHandler;


public class STDUncaughtExceptionHandler extends Exception implements UncaughtExceptionHandler {
    private static final long serialVersionUID = -6094962699800441386L;

    /**
     * 系统默认的UncaughtException处理类
     */
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
     *
     * @param ex
     * @return true:处理了该异常信息;否则返回false
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null)
            return false;

        String crashReport = getCrashReport(ex);
        Logger.e("STDUncaughtExceptionHandler", crashReport);
        return true;
    }

    /**
     * 获取崩溃异常报告
     *
     * @param ex
     * @return
     */
    private String getCrashReport(Throwable ex) {
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
