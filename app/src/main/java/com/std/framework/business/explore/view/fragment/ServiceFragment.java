package com.std.framework.business.explore.view.fragment;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.comm.service.MessageByService;
import com.std.framework.ffmpeg.FFMediaConvert;

/**
 * Description :
 * Author:       lx
 * Create on:  2017/4/13.
 * Modify by：lx
 */
public class ServiceFragment extends BaseFragment implements View.OnClickListener {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_service, null);
        view.findViewById(R.id.btn_send).setOnClickListener(this);
        view.findViewById(R.id.btn_jni).setOnClickListener(this);
        return view;
    }

    private Messenger clientMessager = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getContext(), msg.getData().getString("reply"), Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    });

    private void startService() {
        getActivity().bindService(new Intent(getActivity(), MessageByService.class),
                new ServiceConnection() {

                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Messenger messenger = new Messenger(service);
                        Message msg = Message.obtain(null, 1);
                        msg.replyTo = clientMessager;
                        Bundle bundle = new Bundle();
                        bundle.putString("send", "收到来自客户端的消息！");
                        msg.setData(bundle);
                        try {
                            messenger.send(msg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }

                    @Override
                    public void onBindingDied(ComponentName name) {

                    }
                }, Service.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                startService();
                break;
            case R.id.btn_jni:
                String src = Environment.getExternalStorageDirectory() + "/audio.amr";
                String target = Environment.getExternalStorageDirectory() + "/audio.mp3";
                FFMediaConvert.audioToMp3(src, target);
                break;
        }

    }
}
