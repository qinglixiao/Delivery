package com.std.base.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.fragment.app.Fragment
import com.std.base.mvvm.R
import com.std.base.mvvm.comm.ActionBar

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/11.
 */
abstract class BaseTpFragment : Fragment() {
    var actionBar: ActionBar? = null
        get() {
            return (activity as BaseTpActivity).actionBar
        }
    lateinit var childView: View
    lateinit var stubError: ViewStub

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.root_fragment, null)
        stubError = rootView.findViewById(R.id.stub_error)
        addChildView(rootView)
        return rootView
    }

    private fun addChildView(parent: View) {
        childView = LayoutInflater.from(context).inflate(layoutId(), null)
        parent.findViewById<ViewGroup>(R.id.content).addView(childView,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    override fun onDestroyView() {
        destroyView()
        super.onDestroyView()
    }

    /**
     * 页面布局文件
     */
    abstract fun layoutId(): Int

    open fun initView(savedInstanceState: Bundle?) {}

    abstract fun destroyView()
}