package com.std.framework.basic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.library.imageloader.cache.disc.naming.Md5FileNameGenerator;
import com.library.imageloader.core.ImageLoader;
import com.library.imageloader.core.ImageLoaderConfiguration;
import com.library.imageloader.core.assist.QueueProcessingType;
import com.library.util.LibUtil;
import com.library.util.LogUtil;
import com.std.framework.comm.clazz.STDActivityManager;
import com.std.framework.comm.clazz.STDUncaughtExceptionHandler;
import com.std.framework.core.Logger;
import com.std.framework.util.AppUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        AppUtil.initApp(this);
        configLogSystem();
        setExceptionHandle();
//        initImageLoader(this);
        Logger.d("LX", "App-onCreate");
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    private boolean isBackground = false;
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //应用切到后台
        if(level == TRIM_MEMORY_UI_HIDDEN){
            isBackground = true;
        }
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

    /**
     * 描          述 ：退出系统
     * 创建日期  : 2014-7-14
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @version : 1.0
     */
    public void exit() {
        STDActivityManager.getInstance().finishAll();
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

    private ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            if(isBackground){
                isBackground = false;
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

}
