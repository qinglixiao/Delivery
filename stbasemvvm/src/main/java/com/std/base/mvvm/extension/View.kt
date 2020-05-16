package com.std.base.mvvm.extension

import android.view.View
import android.widget.TextView

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/9.
 */

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun TextView?.setNotNullText(txt: String?) {
    this?.text = txt
}