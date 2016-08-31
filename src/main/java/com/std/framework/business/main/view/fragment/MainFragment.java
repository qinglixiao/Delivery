package com.std.framework.business.main.view.fragment;

import com.library.core.ThreadPool;
import com.library.util.About;
import com.library.util.LibUtil;
import com.std.framework.R;
import com.std.framework.comm.BaiduLocationProvider;
import com.std.framework.comm.BaiduLocationProvider.LocationListener;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentMainBinding;
import com.std.framework.basic.BaseFragment;
import com.std.framework.service.ICallBack;
import com.std.framework.service.IRemoteService;
import com.std.framework.service.InnerService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainFragment extends BaseFragment implements OnClickListener {
    public View view;
    private IRemoteService remoteService;
    private FragmentMainBinding fragmentMainBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_home);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_main, null);
        fragmentMainBinding = DataBindingUtil.bind(view);
        fragmentMainBinding.btnOpen.setOnClickListener(this);
        fragmentMainBinding.btnClose.setOnClickListener(this);
        fragmentMainBinding.btnRequest.setOnClickListener(this);
        fragmentMainBinding.btnLocation.setOnClickListener(this);
        fragmentMainBinding.btnSendSocket.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_open:
                Intent intent = new Intent(getActivity(), InnerService.class);
                getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_close:
                intent = new Intent(getActivity(), InnerService.class);
                getActivity().unbindService(connection);
                break;
            case R.id.btn_request:
                if (remoteService != null) {
                    try {
                        remoteService.remoteAddition(Integer.valueOf(fragmentMainBinding.edtA.getText().toString()),
                                Integer.valueOf(fragmentMainBinding.edtB.getText().toString()));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_location:
                BaiduLocationProvider locationProvider = new BaiduLocationProvider(getActivity());
                locationProvider.setListener(locationListener);
                locationProvider.start();
                break;
            case R.id.btn_send_socket:
                sendSocket();
                break;
        }
    }

    private void sendSocket() {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("172.31.243.9", 8555);
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                    writer.write("来自于手机");
                    writer.write(About.getNetProvider(getContext()));
                    writer.write(LibUtil.getPhoneNumber(getContext()));
                    writer.write(LibUtil.getDeviceIMEI(getContext()));
                    writer.flush();
                    writer.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void on(View view) {

    }


    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onReceiveLocation(String address) {
            // TODO Auto-generated method stub
            fragmentMainBinding.tvResult.setText(address);
        }
    };

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            remoteService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), "连接成功", Toast.LENGTH_LONG).show();
            remoteService = IRemoteService.Stub.asInterface(service);
            try {
                remoteService.registerCallBack(callback);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            fragmentMainBinding.tvResult.setText(msg.getData().getString("result"));
        }

        ;
    };

    private ICallBack.Stub callback = new ICallBack.Stub() {

        @Override
        public void onCallBack(Bundle bundle) throws RemoteException {
            // TODO Auto-generated method stub
            Log.d("LX", Looper.myLooper() == Looper.getMainLooper() ? "==" : "!=");
            Message message = Message.obtain(handler, 7);
            message.setData(bundle);
            message.sendToTarget();
        }
    };

}
