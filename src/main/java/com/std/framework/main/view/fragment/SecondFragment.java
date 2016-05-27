package com.std.framework.main.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.core.FragmentManufacture;
import com.std.framework.core.Logger;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.view.PagerSlidingTabStrip;

public class SecondFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private MyPagerAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_second, null);
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
            initPager();
            tabStrip.setOnPageChangeListener(this);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeView(view);
        return view;
    }

    private void initPager() {
        adapter = new MyPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabStrip.setViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Logger.m(adapter.getItem(position).getClass().getSimpleName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "ButtonImageView", "Top New Free",
                "Trending", "EventBus"};

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
            if (TITLES[position].equals("EventBus"))
                return FragmentManufacture.make(null, EventBusFragment.class);
            else
                return PagerFragment.newInstance(position, TITLES[position]);
        }
    }
}
