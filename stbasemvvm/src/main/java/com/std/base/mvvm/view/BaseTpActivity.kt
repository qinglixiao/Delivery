package com.std.base.mvvm.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.std.base.mvvm.R
import com.std.base.mvvm.comm.ActionBar
import com.std.base.mvvm.comm.DialogShow
import com.std.base.mvvm.extension.inflate

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/8.
 */
abstract class BaseTpActivity : AppCompatActivity() {
    lateinit var rootView: View
    var actionBar: ActionBar? = null
    lateinit var dialog: DialogShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogShow(this)
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(inflate(layoutResID))
    }

    override fun setContentView(view: View?) {
        setup().findViewById<ViewGroup>(R.id.content).addView(view)
        super.setContentView(rootView)
    }

    private fun rootView(): View {
        rootView = inflate(R.layout.root_layout)
        return rootView
    }

    private fun setup(): View {
        return rootView().apply {
            actionBar = onCreateActionBar(ActionBar.Builder())
            actionBar?.get()?.let {
                findViewById<ViewGroup>(R.id.ll_header).addView(it,
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT))
            }
        }
    }

    /**
     * 设置标题栏
     */
    open fun onCreateActionBar(builder: ActionBar.Builder): ActionBar? {
        return null
    }

}