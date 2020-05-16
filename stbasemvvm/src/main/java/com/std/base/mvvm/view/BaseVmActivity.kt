package com.std.base.mvvm.view

import android.os.Bundle

/**
 * Description: mvvm基础类
 * Author: lixiao
 * Create on: 2020/5/7.
 */
abstract class BaseVmActivity : BaseTpActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        fragments()
    }

    abstract fun layoutId(): Int

    open fun fragments() {}
}