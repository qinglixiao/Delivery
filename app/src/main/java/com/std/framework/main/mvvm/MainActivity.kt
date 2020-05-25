package com.std.framework.main.mvvm

import android.os.Bundle
import android.view.View
import com.std.base.mvvm.comm.ActionBar
import com.std.base.mvvm.extension.addFragment
import com.std.base.mvvm.view.BaseVmActivity
import com.std.framework.R

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/9.
 */
class MainActivity : BaseVmActivity() {
    override fun onCreateActionBar(builder: ActionBar.Builder): ActionBar? {
        return builder.title("主页")
                .leftText("返回", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        finish()
                    }
                })
                .build()
    }

    override fun layoutId(): Int {
        return R.layout.activity_main_mvvm
    }

    override fun fragments() {
        addFragment(R.id.frg_container, MainFragment::class, Bundle())
    }
}