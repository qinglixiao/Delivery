package com.std.framework.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.std.framework.R;

public class WidgetUtil {
	/**
	 * 
	 * 描          述 ：弹出系统退出菜单
	 * 创建日期  : 2014-5-28
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context
	 * @param callBackListener
	 *
	 */
	public static void popMenuExit(Activity context, OnClickListener callBackListener) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.exit_pop, null);
		Button btn_exit = (Button) view.findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(callBackListener);

		PopupWindow popupWindow = new PopupWindow(context);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setAnimationStyle(R.style.popWindowAnim);
		popupWindow.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setContentView(view);
		popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0,0);
	}

	/**
	 * 取消或者删除所有状态栏通知
	 *
	 * @param context
	 */
	public static void cancelAllNotification(Context context) {
		((NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
	}
}
