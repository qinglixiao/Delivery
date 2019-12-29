package com.std.framework;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.library.imageloader.cache.disc.naming.Md5FileNameGenerator;
import com.library.imageloader.core.ImageLoader;
import com.library.imageloader.core.ImageLoaderConfiguration;
import com.library.imageloader.core.assist.QueueProcessingType;
import com.library.util.LibUtil;
import com.library.util.LogUtil;
import com.std.framework.comm.clazz.STDUncaughtExceptionHandler;
import com.std.framework.util.AppUtil;

import me.std.common.utils.AppContextUtil;
import me.std.common.utils.Logger;

public class App extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        String processName = AppContextUtil.getProcessName();
        if (!TextUtils.isEmpty(processName) && processName.equals(getPackageName())) {//主进程初始化
            AppUtil.initApp(this);
            AppContextUtil.initApp(this);
            AppContextUtil.registerActivityLifecycle(this);
            configLogSystem();
            setExceptionHandle();
            Logger.d("App-onCreate");
            Logger.d("proc_name:" + AppContextUtil.getProcessName());
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

    /**
     * 描          述 ：配置日志系统
     * 创建日期  : 2014-7-14
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @version : 1.0
     */
    private void configLogSystem() {
        if (LibUtil.isExternalStorageAvailable())
            LogUtil.configureOnlyLogFile(AppUtil.getAppDirectory());
        else
            Log.e("APP", "日志系统配置失败---[设备无存储卡]");
    }

    public void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

}
