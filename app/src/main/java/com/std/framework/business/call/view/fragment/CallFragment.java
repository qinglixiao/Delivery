package com.std.framework.business.call.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.business.call.mutual.CallAssist;
import com.std.framework.business.call.view.RecordVoiceDialog;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentCallBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;

public class CallFragment extends STFragment implements View.OnClickListener {
    private FragmentCallBinding fragmentCallBinding;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle(R.string.main_tab_communicate);
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, null);
        fragmentCallBinding = DataBindingUtil.bind(view);
        playImitate();
        setListener();
        return view;
    }

    private void setListener() {
        fragmentCallBinding.layoutChooseVoice.setOnClickListener(this);
    }

    private Consumer<Long> subscribe = new Consumer<Long>() {
        @Override
        public void accept(Long aLong) throws Exception {
            fragmentCallBinding.vPlayVoice.pbPlay.setProgress(aLong.intValue());
        }
    };


    private void playImitate() {
        Flowable
                .interval(1, 1, TimeUnit.SECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_choose_voice:
                CallAssist.openRecordListActivity(getActivity(), 0);
                break;
        }
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        new RecordVoiceDialog(getActivity()).show();
    }
}

