package com.std.framework.business.find.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.FragmentManufacture;
import com.std.framework.core.Logger;
import com.std.framework.core.NavigationBar;
import com.std.framework.comm.view.PagerSlidingTabStrip;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private MyPagerAdapter adapter = null;
    private Map<String, Class<? extends BaseFragment>> fragments;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_find);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initFragment();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_second, null);
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
            tabStrip.setAllCaps(false);
            initPager();
            tabStrip.setOnPageChangeListener(this);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeView(view);
        return view;
    }

    private void initFragment() {
        fragments = new LinkedHashMap<>();
        fragments.put("EventBus", EventBusFragment.class);
        fragments.put("RxBus", AnimationFragment.RxBusFragment.class);
        fragments.put("CustomerView", CustomerViewFragment.class);
        fragments.put("Animation", AnimationFragment.class);
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

        private final Object[] TITLES = fragments.keySet().toArray();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position].toString();
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentManufacture.make(null, fragments.get(TITLES[position]));
        }
    }
}
