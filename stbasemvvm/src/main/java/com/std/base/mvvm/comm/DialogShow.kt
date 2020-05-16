package com.std.base.mvvm.comm

import android.app.Activity

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/14.
 */
class DialogShow(activity: Activity) : ViewAction {
    override var uiState: UiState.State = UiState.State.STATE_NO
    var activity = activity

    override fun showLoading(txt: String?, outClose: Boolean) {
    }

    override fun hideLoading() {
    }
}