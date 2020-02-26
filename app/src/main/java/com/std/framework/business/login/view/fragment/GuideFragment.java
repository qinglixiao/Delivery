package com.std.framework.business.login.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;

import me.std.base.base.BaseFragment;

public class GuideFragment extends BaseFragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_guide, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
		viewPager.setAdapter(new Pager(getActivity().getSupportFragmentManager()));
	}

	public class Pager extends FragmentPagerAdapter {
		private final int[] resId = new int[] { R.drawable.welcome_p1, R.drawable.welcome_p2, R.drawable.welcome_p3, R.drawable.welcome_p4 };

		public Pager(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return GuidePagerFragment.newInstance(resId[arg0], arg0 != resId.length - 1 ? false : true);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return resId.length;
		}

	}
}
