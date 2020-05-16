package com.std.base.mvvm.extension

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.reflect.KClass

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/13.
 */

fun FragmentActivity.addFragment(@IdRes containerViewId: Int, fragment: () -> Fragment) {
    supportFragmentManager.beginTransaction().add(containerViewId, fragment()).commitAllowingStateLoss()
}

fun <T : Fragment> FragmentActivity.addFragment(@IdRes containerViewId: Int, fragment: KClass<T>, bundle: Bundle?) {
    supportFragmentManager.beginTransaction().add(containerViewId, fragment.java, bundle).commit()
}
