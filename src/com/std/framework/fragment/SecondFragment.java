package com.std.framework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.view.PagerSlidingTabStrip;

public class SecondFragment extends BaseFragment {
	private View view;
	private ViewPager viewPager;
	private PagerSlidingTabStrip tabStrip;
	private int current_page_index = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_second, null);
		viewPager = (ViewPager) view.findViewById(R.id.pager);
		tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		initPager();
		return view;
	}

	private void initPager() {
		viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
		tabStrip.setViewPager(viewPager);
	}
	
	class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid", "Top New Free",
				"Trending" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			current_page_index = position;
			return PagerFragment.newInstance(position);
		}
	}
}
