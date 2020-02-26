package com.std.framework.business.home.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.std.framework.R;

import me.std.base.base.BaseFragment;
import me.std.base.base.STActivity;
import me.std.base.core.ActionBar;

public class MainActivity extends STActivity {
	private static final String TAG = "LX";
	private long mExitTime = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onActionBar(ActionBar.Builder builder) {
		builder.setTitle(R.string.app_name);
	}

	public void remove(BaseFragment fragment) {
//		FragmentManager.remove(fragment);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.d(TAG, "onConfigurationChanged");
	}

}
