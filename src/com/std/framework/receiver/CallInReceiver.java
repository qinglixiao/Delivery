package com.std.framework.receiver;

import com.std.framework.activity.MainActivity;
import com.std.framework.comm.STDNotificationManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallInReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		STDNotificationManager notificationManager = STDNotificationManager.getInstance(context);
		notificationManager.notify(new Intent(context, MainActivity.class));
	}

}
