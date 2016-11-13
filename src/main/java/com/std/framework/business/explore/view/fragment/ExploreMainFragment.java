package com.std.framework.business.explore.view.fragment;

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
import com.std.framework.comm.view.PagerSlidingTabStrip;
import com.std.framework.core.FragmentManufacture;
import com.std.framework.core.NavigationBar;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExploreMainFragment extends BaseFragment {
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private MyPagerAdapter adapter = null;
    private static Map<String, Class<? extends BaseFragment>> fragments;

    static {
        fragments = new LinkedHashMap<>();
        fragments.put("EventBus", EventBusFragment.class);
        fragments.put("RxBus", AnimationFragment.RxBusFragment.class);
        fragments.put("CustomerView", CustomerViewFragment.class);
        fragments.put("Animation", AnimationFragment.class);
        fragments.put("RecyclerView",RecyclerViewFragment.class);
        fragments.put("EffectView",EffectFragment.class);
        fragments.put("Animation2",AnimationFragment2.class);
    }

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_explore);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_second, null);
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
            tabStrip.setTextColorResource(R.color.tab_strip_text_color);
            tabStrip.setAllCaps(false);
            initPager();
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
