package com.std.framework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.fragment.FiveFragment;
import com.std.framework.fragment.FourFragment;
import com.std.framework.fragment.MainFragment;
import com.std.framework.fragment.SecondFragment;
import com.std.framework.fragment.ThreeFragment;

public class MainTabActivity extends BaseActivity implements OnTabChangeListener{
	private static final String PAGE_ONE = "page_one";
	private static final String PAGE_TWO = "page_two";
	private static final String PAGE_THREE = "page_three";
	private static final String PAGE_FOUR = "page_four";
	private static final String PAGE_FIVE = "page_five";

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
		View view = layoutInflater.inflate(R.layout.tab_wiget_item, null);
		view.findViewById(R.id.tab_main).setBackgroundResource(R.drawable.abs__tab_indicator_ab_holo);
		((ImageView) view.findViewById(R.id.tab_img)).setImageResource(imgResId);
		((TextView) view.findViewById(R.id.tab_tv)).setText(text);
		return view;
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
