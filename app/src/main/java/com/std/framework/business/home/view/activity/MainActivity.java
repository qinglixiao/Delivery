package com.std.framework.business.home.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.basic.BaseFragment;

public class MainActivity extends BaseTitleActivity {
	private static final String TAG = "LX";
	private long mExitTime = 0;

	@Override
	public void onNavigationBar(NavigationBar navigation) {
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

}
