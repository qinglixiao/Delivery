package com.std.framework.main.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.std.framework.R;
import com.std.framework.activity.App;
import com.std.framework.activity.BaseTitleActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.fragment.Html5NativeCommunicationFragment;

public class MainActivity extends BaseTitleActivity {
	private static final String TAG = "LX";
	private long mExitTime = 0;

	@Override
	protected void onNavigationBar(NavigationBar navigation) {
		navigation.setTitle(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
