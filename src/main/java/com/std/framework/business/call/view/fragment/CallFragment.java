package com.std.framework.business.call.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.business.call.mutual.ConfigCall;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentCallBinding;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CallFragment extends BaseFragment implements View.OnClickListener {
    private FragmentCallBinding fragmentCallBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_communicate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, null);
        fragmentCallBinding = DataBindingUtil.bind(view);
        playImitate();
        setListener();
        return view;
    }

    private void setListener() {
        fragmentCallBinding.layoutChooseVoice.setOnClickListener(this);
    }

    private Subscriber<Long> subscribe = new Subscriber<Long>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Long aLong) {
            fragmentCallBinding.vPlayVoice.pbPlay.setProgress(aLong.intValue());
        }
    };

    private void playImitate() {
        Observable
                .interval(1, 1, TimeUnit.SECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_choose_voice:
                startActivity(new Intent(ConfigCall.ACTION_VOICE_RECORD));
                break;
        }
    }
}
