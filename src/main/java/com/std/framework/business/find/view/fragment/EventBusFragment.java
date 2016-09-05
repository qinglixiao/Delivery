package com.std.framework.business.find.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.std.framework.R;
import com.std.framework.core.NavigationBar;
import com.std.framework.basic.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by gfy on 2016/4/1.
 */
public class EventBusFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "LX";
    private View view;
    private Button btn_send;
    private Button btn_asyn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_event_bus, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_asyn = (Button) view.findViewById(R.id.btn_asyn);
        btn_send.setOnClickListener(this);
        btn_asyn.setOnClickListener(this);
    }

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                Log.d(TAG, "send-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
                EventBus.getDefault().post(new Param(""));
                break;
            case R.id.btn_asyn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "send-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
                        EventBus.getDefault().post(new Param(""));
                    }
                }).start();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Param param) {
        Log.d(TAG, "onEventMainThread-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventPostThread(Param param) {
        Log.d(TAG, "onEventPostThread-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(Param param) {
        Log.d(TAG, "onEventAsync-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackground(Param param) {
        Log.d(TAG, "onEventBackground-------" + "thread_id:" + Thread.currentThread().getId() + "--thread_name:" + Thread.currentThread().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    class Param {
        String message;

        public Param(String message) {
            this.message = message;
        }
    }
}
