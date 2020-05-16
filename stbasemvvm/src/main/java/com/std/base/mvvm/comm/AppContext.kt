package com.std.base.mvvm.comm

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/7.
 */
class AppContext {
    companion object {
        //debug模式
        var debug: Boolean? = true
            get() {
                return (0 != context?.applicationInfo?.flags?.and(ApplicationInfo.FLAG_DEBUGGABLE))
            }

        //当前可见Activity
        var topActivity: WeakReference<Activity>? = null

        lateinit var context: Context

        fun init(application: Application) {
            context = application
            registerLifeCallBack()
        }

        fun registerLifeCallBack() {
            (context as Application).registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStarted(activity: Activity) {
                    topActivity = WeakReference(activity)
                }

                override fun onActivityDestroyed(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityStopped(activity: Activity) {
                    topActivity?.clear()
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                }

                override fun onActivityResumed(activity: Activity) {
                }

            })
        }
    }
}