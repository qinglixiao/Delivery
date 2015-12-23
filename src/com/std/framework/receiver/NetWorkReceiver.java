package com.std.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.std.framework.comm.STDNotificationManager;

public class NetWorkReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		STDNotificationManager.getInstance(context).notify("网络变化通知");
	}

}
