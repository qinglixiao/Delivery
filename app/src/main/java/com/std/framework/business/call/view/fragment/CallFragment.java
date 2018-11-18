package com.std.framework.business.call.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.basic.BaseTitleFragment;
import com.std.framework.business.call.mutual.CallAssist;
import com.std.framework.business.call.view.RecordVoiceDialog;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentCallBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CallFragment extends BaseTitleFragment implements View.OnClickListener {
    private FragmentCallBinding fragmentCallBinding;

    @Override
    public void onNavigationBar(NavigationBar.Builder navBuilder) {
        navBuilder.setTitle(R.string.main_tab_communicate);
//        navBuilder.addRightButton(R.drawable.rich_edit_add, new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (PermissionAssist.checkPermission(getContext(), PermissionAssist.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                    PermissionAssist.requestPermissions(getActivity(), PermissionAssist.RECORD_AUDIO);
//                } else {
//                    new RecordVoiceDialog(getContext()).show();
//                }
//                return false;
//            }
//        });
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

