package com.std.framework.comm.view.desk;

import com.std.framework.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class DeskWidgetProvider extends AppWidgetProvider {
	private static final String click_1 = "com.std.click";
	private static RemoteViews views;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		if (intent.getAction().equals(click_1)) {
			Log.d("LX", "onReceive");
			views.setTextViewText(R.id.textView1, "改变文本");
			ComponentName com = new ComponentName(context,DeskWidgetProvider.class);
			manager.updateAppWidget(com, views);
			Log.d("LX", "pass");
			Toast.makeText(context, "Click Button DeskWidgetProvider", Toast.LENGTH_SHORT).show();
		}
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		for (int appWidgetId : appWidgetIds) {
			views = new RemoteViews(context.getPackageName(), R.layout.deskwidget);
			Intent intent = new Intent().setAction(click_1);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.btn_refresh, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
