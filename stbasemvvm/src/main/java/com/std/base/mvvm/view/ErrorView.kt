package com.std.base.mvvm.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import com.std.base.mvvm.R

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/15.
 */
class ErrorView : LinearLayout {
    private lateinit var v_pic: ImageView
    private lateinit var tv_msg: TextView

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.error_layout, this)
        v_pic = findViewById(R.id.img_msg)
        tv_msg = findViewById(R.id.tv_error_msg)
    }

    fun setPicture(@DrawableRes resId: Int) {
        v_pic.setImageResource(resId)
    }

    fun setError(error: String) {
        tv_msg.text = error
    }
}