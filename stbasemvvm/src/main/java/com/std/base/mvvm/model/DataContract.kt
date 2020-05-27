package com.std.base.mvvm.model

import android.content.Context
import com.std.base.mvvm.comm.AppContext
import me.std.common.utils.AppContextUtil

/**
 * Description:Model层最低层接口
 * Author: lixiao
 * Create on: 2020/5/7.
 */
interface DataContract {
    val context: Context
        get() {
            return AppContextUtil.getAppContext()
        }

    suspend fun loadData()
}