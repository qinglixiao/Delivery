package com.std.framework.comm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.std.framework.R;
import com.std.framework.activity.MainActivity;


public class STDNotificationManager {
	private static STDNotificationManager instance;
	private Context context;
	private NotificationManager notificationManager;
	private RemoteViews defContenView;
	private PendingIntent defPendingIntent;
	
	private STDNotificationManager(Context context){
		this.context = context;
		init();
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
	
	private void init(){
		notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		defContenView =  new RemoteViews(context.getPackageName(), R.layout.notification);
		defPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
	}
	
	public void notify(String message){
		Notification notification = newNotification(defContenView,defPendingIntent);
		notification.contentView.setTextViewText(R.id.notification_tv_message, message);
		notificationManager.notify(0, notification);
	}
	
	private Notification newNotification(RemoteViews contentViews,PendingIntent pendingIntent){
		Notification notification = new Notification();
		notification.contentView = contentViews;
		notification.contentIntent = pendingIntent;
		return notification;
	}
	
}
