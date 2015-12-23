package com.std.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.std.framework.comm.STDNotificationManager;

public class CallInReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		STDNotificationManager notificationManager = STDNotificationManager.getInstance(context);
//		notificationManager.notify("网络发生变化");
	}

}
