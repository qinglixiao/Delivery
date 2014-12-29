package com.std.framework.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.std.framework.R;
import com.std.framework.fragment.MainFragment;
import com.std.framework.fragment.SecondFragment;

import android.R.color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class MainTabActivity extends SherlockFragmentActivity{
	private FragmentTabHost mTabHost;
	private LayoutInflater layoutInflater;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_tab_main);
		layoutInflater = getLayoutInflater();
		initViewTab();
	}

	private void initViewTab() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setStripEnabled(false);
		mTabHost.getTabWidget().setRightStripDrawable(color.white);
		mTabHost.getTabWidget().setLeftStripDrawable(color.white);

//		mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"), MainFragment.class, null);
//		mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Simple"), SecondFragment.class, null);
//		mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Simple"), MainFragment.class, null);
//		mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Simple"), MainFragment.class, null);
	
		TabSpec defalutSpec = mTabHost.newTabSpec("");
		View defaultIndicator = newIndicator(R.drawable.abs__tab_indicator_ab_holo, R.drawable.ic_launcher, "默认页");
		defalutSpec.setIndicator(defaultIndicator);
		mTabHost.addTab(defalutSpec,MainFragment.class, null);
		
		defalutSpec = mTabHost.newTabSpec("a");
		defaultIndicator = newIndicator(R.drawable.abs__tab_indicator_ab_holo, R.drawable.ic_launcher, "默认页");
		defalutSpec.setIndicator(defaultIndicator);
		mTabHost.addTab(defalutSpec,SecondFragment.class, null);
		
		
		mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(color.white);
		mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(color.white);
//		mTabHost.getTabWidget().getChildAt(2).setBackgroundResource(color.white);
		
	}
	
	private View newIndicator(int bgResId, int imgResId,String text){
		View view = layoutInflater.inflate(R.layout.tab_indicator_item,null);
		view.findViewById(R.id.tab_indicator_content).setBackgroundResource(bgResId);
		((ImageView)view.findViewById(R.id.img_tab_indicator)).setImageResource(imgResId);
		((TextView)view.findViewById(R.id.tv_tab_indicator)).setText(text);
		return view;
	}

	public void onDestroy() {
		super.onDestroy();
		mTabHost = null;
	}
}
