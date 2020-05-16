package com.std.base.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.std.base.mvvm.comm.UiState
import com.std.base.mvvm.exception.Failure

/**
 * Description:vm层最低层类
 * Author: lixiao
 * Create on: 2020/5/7.
 */
abstract class BaseViewModel : ViewModel() {
    var uiState: MutableLiveData<UiState> = MutableLiveData()

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    protected fun changeUiState(uiState: UiState) {
        this.uiState.value = uiState
    }

}