package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleFragment;
import com.std.framework.core.NavigationBar;
import com.std.network.NetworkConfig;
import com.std.network.request.NetCallBack;
import com.std.network.request.Result;
import com.std.network.request.STRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import me.std.common.core.ThreadPool;
import me.std.common.utils.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gfy on 2016/4/1.
 */
public class EventBusFragment extends BaseTitleFragment implements View.OnClickListener {
    private static final String TAG = "LX";
    private View view;
    private Button btn_send;
    private Button btn_asyn;
    private Button btn_request;

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
        btn_request = view.findViewById(R.id.btn_request);
        btn_send.setOnClickListener(this);
        btn_asyn.setOnClickListener(this);
        btn_request.setOnClickListener(this);
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
            case R.id.btn_request:
                STRequest request = new STRequest.Builder()
                        .url("https://www.hao123.com")
                        .build();
                request.get(new NetCallBack<String>() {
                    @Override
                    public void onResult(Result<String> result, Error error) {

                    }
                }, String.class);
//                request.url("http://119.29.29.29/d?dn=www.baidu.com&ttl=1");

//                request.addParameter("client_id",NetworkConfig.APPID);
//                request.addParameter("response_type","code");
//                request.addParameter("redirect_uri","https://www.chunyuyisheng.com/");

//                STRequest request = new STRequest();
//                request.url("/action/oauth2/authorize");
//                request.addParameter("client_id",NetworkConfig.APPID);
//                request.addParameter("response_type","code");
//                request.addParameter("redirect_uri","https://www.chunyuyisheng.com/");
//                request.get(new NetCallBack<String>() {
//                    @Override
//                    public void onResult(Result<String> result, Error error) {
//                    }
//                }, String.class);
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

    @Override
    public void onNavigationBar(NavigationBar.Builder navBuilder) {

    }


    class Param {
        String message;

        public Param(String message) {
            this.message = message;
        }
    }
}
