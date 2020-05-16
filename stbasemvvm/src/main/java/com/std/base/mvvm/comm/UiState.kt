package com.std.base.mvvm.comm
import com.std.base.mvvm.extension.empty

class UiState(state: State = State.STATE_NO) {

    var state: State = state

    var outClosed: Boolean = true

    var text: String? = String.empty()

    enum class State {
        STATE_NO,
        STATE_SHOW_LOAD,
        STATE_HIDE_LOAD,
    }

    fun change(view: ViewAction) {
        when (state) {
            State.STATE_NO -> {
                if (view.uiState == State.STATE_SHOW_LOAD) {
                    view.hideLoading()
                }
            }
            State.STATE_SHOW_LOAD -> {
                if (view.uiState != state) {
                    view.showLoading(text, outClosed)
                }
            }
            State.STATE_HIDE_LOAD -> {
                if (view.uiState == State.STATE_SHOW_LOAD) {
                    view.hideLoading()
                }
            }
        }
        view.uiState = state
    }
}