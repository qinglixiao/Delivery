package me.std.base.widget.toptab.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import me.std.base.R;
import me.std.base.base.ChunyuActivity;
import me.std.base.widget.toptab.adapter.CYViewPagerAdapter;


/**
 * Created by Roger Huang on 2019/1/17.
 */

public abstract class CYTopTabActivity extends ChunyuActivity {

    TabLayout mTabLayout;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_top_tab_page);

        mViewPager = findViewById(R.id.tab_content);
        mTabLayout = findViewById(R.id.tab_layout);

        initTab();
    }

    protected void initTab() {
        mViewPager.setAdapter(getPagerAdapter());

        mTabLayout.setupWithViewPager(mViewPager);

        updateTabTitle(getTabTitles());
    }

    protected abstract CYViewPagerAdapter getPagerAdapter();

    protected abstract List<String> getTabTitles();

    public void updateTabTitle(List<String> titles) {
        if (titles == null) return;

        mTabLayout.removeAllTabs();
        for (String name: titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(name));
        }
    }
}