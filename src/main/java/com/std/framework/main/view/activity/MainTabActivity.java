package com.std.framework.main.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.activity.BaseTitleActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.main.view.fragment.FiveFragment;
import com.std.framework.main.view.fragment.FourFragment;
import com.std.framework.main.view.fragment.MainFragment;
import com.std.framework.main.view.fragment.SecondFragment;
import com.std.framework.main.view.fragment.ThreeFragment;

public class MainTabActivity extends BaseTitleActivity implements OnTabChangeListener{
	private static final String PAGE_ONE = "page_one";
	private static final String PAGE_TWO = "page_two";
	private static final String PAGE_THREE = "page_three";
	private static final String PAGE_FOUR = "page_four";
	private static final String PAGE_FIVE = "page_five";

	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_tab_main);
		initViewTab();
	}

	private void initViewTab() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		mTabHost.addTab(mTabHost.newTabSpec(PAGE_ONE).setIndicator(
				newIndicator(R.drawable.ali144, "one")), MainFragment.class, null);

		mTabHost.addTab(mTabHost.newTabSpec(PAGE_TWO).setIndicator(
				newIndicator(R.drawable.ali144, "two")), SecondFragment.class, null);

		mTabHost.addTab(mTabHost.newTabSpec(PAGE_THREE).setIndicator(
				newIndicator(R.drawable.ali144, "three")), ThreeFragment.class, null);
		
		mTabHost.addTab(mTabHost.newTabSpec(PAGE_FOUR).setIndicator(
				newIndicator(R.drawable.ali144, "four")), FourFragment.class, null);
		
		mTabHost.addTab(mTabHost.newTabSpec(PAGE_FIVE).setIndicator(
				newIndicator(R.drawable.ali144, "five")), FiveFragment.class, null);
		
		mTabHost.setOnTabChangedListener(this);
		mTabHost.setCurrentTabByTag(PAGE_THREE);
	}

	private View newIndicator(int imgResId, String text) {
		View view = View.inflate(this,R.layout.tab_wiget_item, null);
		((ImageView) view.findViewById(R.id.tab_img)).setImageResource(imgResId);
		((TextView) view.findViewById(R.id.tab_tv)).setText(text);
		return view;
	}

	@Override
	protected void onNavigationBar(NavigationBar navigation) {
		super.onNavigationBar(navigation);
		navigation.setTitle(R.string.app_name);
		navigation.setNavigationIcon(R.drawable.icon);
	}

	public void onDestroy() {
		super.onDestroy();
		mTabHost = null;
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		mTabHost.getCurrentTabView().findViewById(R.id.tab_adorn).setVisibility(View.GONE);
	}
}
