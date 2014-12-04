package com.std.framework.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.std.framework.R;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.fragment.GuideFragment;
import com.std.framework.util.AppUtil;

public class LaunchActivity extends SherlockFragmentActivity {
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				redirectToMain();
			}
		};
	};

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		Log.d("LX", "进入启动页");
		setContentView(R.layout.launch);
		SharedPreferences preferences = AppUtil.getAppPreferences(this);
		Log.d("LX", "检查是否首次启动");
		if (preferences != null && preferences.getBoolean("isfirst", true)) {
			Log.d("LX", "进入导航页");
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.guide_content, new GuideFragment(handler));
			transaction.commit();
			AppUtil.saveBoolean(this, "isfirst", false);
		}
		else
			handler.postDelayed(waiting, 3000);
		STDActivityManager.getInstance().add(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		handler.removeCallbacks(waiting);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		STDActivityManager.getInstance().remove(this);
	}

	private Runnable waiting = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.sendEmptyMessage(1);
		}

	};

	private void redirectToMain() {
		Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
