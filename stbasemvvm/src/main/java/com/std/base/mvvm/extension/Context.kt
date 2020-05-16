package com.std.base.mvvm.extension

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/9.
 */

fun Context.inflate(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(this).inflate(layoutResId, null)
}

/**
 * 显示或隐藏软键盘
 */
fun Context?.showOrHideKeyboard(view: View?, isShow: Boolean = false) {
    this?.run {
        if (view != null) {
            (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?)?.run {
                if (isShow){
                    showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN)
                } else {
                    hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }
    }
}