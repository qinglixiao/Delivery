package com.std.framework.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.std.framework.R;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.fragment.Html5NativeCommunicationFragment;

public class MainActivity extends BaseTitleActivity {
	private static final String TAG = "LX";
	private long mExitTime = 0;
	private FrameLayout contentLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null)
			FragmentManager.replace(R.id.content, new Html5NativeCommunicationFragment());
	}

	public void remove(BaseFragment fragment) {
		FragmentManager.remove(fragment);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.d(TAG, "onConfigurationChanged");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一下退出系统", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			}
			else {
				App.instance.exit();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
