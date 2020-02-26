package me.std.base.widget.toptab.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.std.base.R;
import me.std.base.base.STFragment;
import me.std.base.widget.toptab.adapter.CYViewPagerAdapter;

/**
 * Created by Roger Huang on 2019/1/17.
 */

public class CYTopTabFragment extends STFragment {
    TabLayout mTabLayout;

    ViewPager mViewPager;

    @Override
    protected View onCreateLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_top_tab_page, null);
        mViewPager = view.findViewById(R.id.tab_content);
        mTabLayout = view.findViewById(R.id.tab_layout);
        initTab();
        return view;
    }

    void initTab() {
        mViewPager.setAdapter(getPagerAdapter());

        mTabLayout.setupWithViewPager(mViewPager);

        updateTabTitle();
    }

    CYViewPagerAdapter getPagerAdapter() {
        return null;
    }

    List<String> getTabTitles() {
        return null;
    }

    void updateTabTitle() {
        for (String name: getTabTitles()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(name));
        }
    }
}
