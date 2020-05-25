package com.std.framework;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.library.imageloader.cache.disc.naming.Md5FileNameGenerator;
import com.library.imageloader.core.ImageLoader;
import com.library.imageloader.core.ImageLoaderConfiguration;
import com.library.imageloader.core.assist.QueueProcessingType;
import com.library.util.LogUtil;
import com.std.framework.comm.clazz.STDUncaughtExceptionHandler;
import com.std.framework.util.AppUtil;
import com.std.network.NetworkConfig;

import me.std.common.utils.AppContextUtil;
import me.std.common.utils.FileUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = AppContextUtil.getProcessName();
        if (!TextUtils.isEmpty(processName) && processName.equals(getPackageName())) {//主进程初始化
            AppUtil.initApp(this);
            AppContextUtil.initApp(this);
            AppContextUtil.registerActivityLifecycle(this);
            configLogSystem();
            setExceptionHandle();
            NetworkConfig.initNetClient();

            DoraemonKit.install(this);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void setExceptionHandle() {
        Thread.setDefaultUncaughtExceptionHandler(new STDUncaughtExceptionHandler());
    }

    private void configLogSystem() {
        LogUtil.configureOnlyLogFile(FileUtil.getLogCacheFile().getAbsolutePath());
    }
}
