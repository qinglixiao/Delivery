package com.std.framework.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MessageByService extends Service {
    private Handler serviceHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(MessageByService.this,msg.getData().getString("send"),Toast.LENGTH_LONG).show();
                    Message client = Message.obtain(null,0);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","收到服务端消息！");
                    client.setData(bundle);
                    try {
                        msg.replyTo.send(client);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
            super.handleMessage(msg);
        }
    };

    private Messenger messager = new Messenger(serviceHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messager.getBinder();
    }
}
