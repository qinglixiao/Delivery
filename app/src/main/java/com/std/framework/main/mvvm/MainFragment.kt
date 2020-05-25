package com.std.framework.main.mvvm

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.std.base.mvvm.extension.observe
import com.std.base.mvvm.model.DataContract
import com.std.base.mvvm.view.BaseVmFragment
import com.std.framework.R
import com.std.framework.databinding.FragmentMainMvvmBinding
import com.std.framework.main.mvvm.model.MainContract
import com.std.framework.main.mvvm.viewmodel.MainViewModel
import com.std.framework.main.mvvm.model.MainModel

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/12.
 */
class MainFragment : BaseVmFragment<FragmentMainMvvmBinding, MainViewModel>() {
    lateinit var bookModel: DataContract

    override fun createViewModel(params: List<Any?>): MainViewModel {
        return MainViewModel(params[0] as MainContract)
    }

    override fun initModel() {
        bookModel = MainModel()
    }

    override fun onSubcribeUi(viewModel: MainViewModel, bindView: FragmentMainMvvmBinding) {
        observe(viewModel.book, {
            var info: StringBuffer = StringBuffer()
            it?.forEach { book ->
                info.append("书名:${book.name}  作者:${book.author}  价格:${book.price} \n")
            }
            bindView.tvShow.setText(info.toString())
        })

        bindView.btnConnect.setOnClickListener {
            viewModel.refresh()
        }

        bindView.btnError.setOnClickListener {
//            viewModel.showError()
            Log.d("LX",Thread.currentThread().stackTrace.asList().toString())
        }

    }

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return injectViewModel(bookModel)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main_mvvm
    }

    override fun destroyView() {

    }

}