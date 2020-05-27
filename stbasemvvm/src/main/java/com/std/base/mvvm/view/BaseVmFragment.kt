package com.std.base.mvvm.view

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.std.base.mvvm.comm.UiState
import com.std.base.mvvm.exception.Failure
import com.std.base.mvvm.extension.observe
import com.std.base.mvvm.extension.viewModels
import com.std.base.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.cancel

/*
 * Description: mvvm基础类
 * Author: lixiao
 * Create on: 2020/5/9.
 */
abstract class BaseVmFragment<VB : ViewDataBinding, VM : BaseViewModel> : BaseTpFragment() {
    lateinit var bindView: VB

    private val viewmodel: ViewModel by viewModels {
        viewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView = DataBindingUtil.bind<VB>(childView)!!
        bindView.lifecycleOwner = this
        initModel()
        subcribeUi(viewmodel as VM)
    }

    open fun onBackPressed() {}

    private fun subcribeUi(viewModel: VM) {
        observe(viewModel.failure, ::handleFailure)
        observe(viewModel.uiState, ::uiStateChanged)
        onSubcribeUi(viewModel, bindView)
    }

    /**
     * 创建Model数据层
     */
    open fun initModel() {}

    /**
     * 在此做LiveData的监听
     */
    abstract fun onSubcribeUi(viewModel: VM, bindView: VB)

    /**
     * 创建ViewModel
     * 参数来源于
     * @see injectViewModel(vararg params: Any?)
     */
    abstract fun createViewModel(params: List<Any?>): VM

    /**
     * 此方法很重要
     * 1.如果业务ViewModel为无参构造则无需重写此方法
     * 2.如果业务ViewModel需要传参则需要重写方法并将参数通过
     * 调用
     * @see injectViewModel
     *
     */
    open fun viewModelFactory(): ViewModelProvider.Factory {
        return injectViewModel(null)
    }

    /**
     * 给ViewModel传递参数，参数将最终传递给
     * @see createViewModel(params: List<Any?>)
     */
    open fun injectViewModel(vararg params: Any?): ViewModelProvider.Factory {
        return LiveDataVMFactory(params.asList() as List<Any>)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            Failure.netError -> {
                var v = stubError.inflate()
            }

            Failure.serverError -> {

            }

            else -> {
                onFailure(failure)
            }
        }
    }

    /**
     * 业务个性错误处理
     */
    open fun onFailure(failure: Failure?) {

    }

    private fun uiStateChanged(state: UiState?) {
        state?.change((activity as BaseTpActivity).dialog)
    }

    override fun destroyView() {
        bindView.unbind()
        viewmodel.viewModelScope.cancel()
    }

    inner class LiveDataVMFactory(params: List<Any>) : ViewModelProvider.Factory {
        private var p: List<Any> = params
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return createViewModel(p) as T
        }
    }
}