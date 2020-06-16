package com.std.framework.main.mvvm

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.std.base.mvvm.extension.observe
import com.std.base.mvvm.model.DataContract
import com.std.base.mvvm.view.BaseVmFragment
import com.std.framework.R
import com.std.framework.databinding.FragmentMainMvvmBinding
import com.std.framework.main.mvvm.model.MainContract
import com.std.framework.main.mvvm.model.MainModel
import com.std.framework.main.mvvm.viewmodel.MainViewModel
import me.std.flutterbridge.ISFlutterActivity
import me.std.flutterbridge.bridge.handlers.BridgeOpenFlutterHandlerK
import me.std.flutterbridge.bridge.specs.FlutterPageParameter

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
            bindView.tvShow.text = info.toString()
        })

        bindView.btnConnect.setOnClickListener {
            viewModel.refresh()
        }

        bindView.btnError.setOnClickListener {
//            viewModel.showError()
            Log.d("LX", Thread.currentThread().stackTrace.asList().toString())
        }

        bindView.btnFlutter.setOnClickListener {
//            activity?.startActivityForResult(ISFlutterActivity.withCachedEngine("flutter_engine")?.build(activity!!),100)
            var params: FlutterPageParameter = FlutterPageParameter("good_list_page", "原生标题")
            params.parameters = mapOf("id" to 35)
            BridgeOpenFlutterHandlerK.openFlutterPage(context, params, 100)
//            activity?.startActivity(ISFlutterActivity.withNewEngine()
//                    .initialRoute("good_list_page")
//                    ?.build(activity!!))
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