package com.std.framework.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.IBasePresenter;
import com.std.framework.core.RxBus;
import com.std.framework.databinding.FragmentRxbusBinding;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.main.contract.RxBusContract;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Description :
 * Created by lx on 2016/7/13.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class RxBusFragment extends BaseFragment implements RxBusContract.View,View.OnClickListener{
    private FragmentRxbusBinding fragmentRxbusBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxbus, null);
        fragmentRxbusBinding = DataBindingUtil.bind(view);
        setListener();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<String> observable = RxBus.getDefault().toObservable(String.class);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                fragmentRxbusBinding.tvShow.setText(s);
            }
        });
    }

    private void setListener(){
        fragmentRxbusBinding.btnSend.setOnClickListener(this);
    }

    @Override
    public void setPresenter(IBasePresenter presenter) {

    }

    @Override
    public void onClick(View v) {
        RxBus.getDefault().post("你好");
    }

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            fragmentRxbusBinding.tvShow.getEditableText().append(s);
        }
    };
}
