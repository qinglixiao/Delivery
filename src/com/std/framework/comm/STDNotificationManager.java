package com.std.framework.comm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class STDNotificationManager {
	private static STDNotificationManager instance;
	private Context context;
	private NotificationManager notificationManager;
	
	private STDNotificationManager(Context context){
		this.context = context;
		notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public static STDNotificationManager getInstance(Context context){
		if(instance == null){
			synchronized(STDNotificationManager.class){
				if(instance == null){
					instance = new STDNotificationManager(context.getApplicationContext());
				}
			}
		}
		return instance;
	}
	
	public void notify(Intent intent){
		Notification notification = new Notification();
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		notification.contentIntent = pendingIntent;
		notificationManager.notify(0, notification);
	}
	
}
