package com.std.base.mvvm.comm

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/8.
 */
interface ViewAction {
    var uiState: UiState.State
    fun showLoading(txt: String?, outClose: Boolean = true)
    fun hideLoading()
}