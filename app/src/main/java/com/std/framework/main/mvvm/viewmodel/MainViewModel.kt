package com.std.framework.main.mvvm.viewmodel

import androidx.lifecycle.viewModelScope
import com.std.base.mvvm.comm.UiState
import com.std.base.mvvm.exception.Failure
import com.std.base.mvvm.viewmodel.BaseViewModel
import com.std.framework.main.mvvm.model.MainContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/12.
 */
class MainViewModel(model: MainContract) : BaseViewModel() {
    private var model = model
    var book = model._books

    fun refresh() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                changeUiState(UiState(UiState.State.STATE_SHOW_LOAD))
                model.loadData()
                book.value = model.getBooks()
                changeUiState(UiState(UiState.State.STATE_HIDE_LOAD))
            }
        }
    }

    fun showError() {
        handleFailure(Failure.netError)
    }
}