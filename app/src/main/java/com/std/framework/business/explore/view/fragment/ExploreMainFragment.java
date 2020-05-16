package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.std.framework.R;
import com.std.framework.comm.view.PagerSlidingTabStrip;
import com.std.framework.core.FragmentManufacture;

import java.util.LinkedHashMap;
import java.util.Map;

import me.std.base.base.BaseFragment;
import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;

public class ExploreMainFragment extends STFragment {
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
        fragments.put("Finger",FingerIdentifyFragment.class);
        fragments.put("progress",ProgressBarFragment.class);
        fragments.put("Invoke",InvokeFragment.class);
        fragments.put("VPN",VPNFragment.class);
        fragments.put("Profiler",ProfilerFragment.class);
        fragments.put("Service",ServiceFragment.class);
        fragments.put("Router",RouterFragment.class);
    }

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle(R.string.main_tab_explore).build();
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
