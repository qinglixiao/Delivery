package com.std.framework.business.call.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.parser.BindingExpressionParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentCallBinding;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CallFragment extends BaseFragment {
    private FragmentCallBinding fragmentCallBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_communicate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, null);
        fragmentCallBinding = DataBindingUtil.bind(view);
        return view;
    }

    private void playImitate() {
        Observable
                .interval(1000, TimeUnit.MILLISECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                });
    }


}
