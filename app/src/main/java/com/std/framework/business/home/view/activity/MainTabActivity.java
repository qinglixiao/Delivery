package com.std.framework.business.home.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.std.framework.R;
import com.std.framework.business.call.view.fragment.CallFragment;
import com.std.framework.business.contact.view.ContactFragment;
import com.std.framework.business.explore.view.fragment.ExploreMainFragment;
import com.std.framework.business.home.view.fragment.MainFragment;
import com.std.framework.business.mine.view.MineFragment;
import com.std.framework.comm.view.MainBottomView;
import com.std.framework.databinding.ActivityTabMainBinding;
import com.std.framework.util.ThemeUtil;

import java.util.List;

import me.std.base.base.STActivity;
import me.std.base.core.ActionBar;
import me.std.location.fragment.STMapFragment;

public class MainTabActivity extends STActivity {
    private ActivityTabMainBinding activityTabMain;

    private MainBottomView.TabSpec exploreTab;
    private MainBottomView.TabSpec contactTab;
    private MainBottomView.TabSpec homeTab;
    private MainBottomView.TabSpec communicateTab;
    private MainBottomView.TabSpec settingTab;
    private MainBottomView.TabSpec mapTab;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        View view = View.inflate(this, R.layout.activity_tab_main, null);
        activityTabMain = DataBindingUtil.bind(view);
        setContentView(view);
        initTab();
    }

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle(R.string.app_name);
    }

    private void initTab() {
        //地图
        mapTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_list, R.string.main_tab_explore, STMapFragment.class);
        activityTabMain.tabContainer.addTab(mapTab);
        //探索
        exploreTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_list, R.string.main_tab_explore, ExploreMainFragment.class);
        activityTabMain.tabContainer.addTab(exploreTab);
        //通讯录
        contactTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_contact, R.string.main_tab_contact, ContactFragment.class);
        activityTabMain.tabContainer.addTab(contactTab);
        //主页
        homeTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_home, R.string.main_tab_home, MainFragment.class);
        activityTabMain.tabContainer.addTab(homeTab);
        //群呼
        communicateTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_communicate, R.string.main_tab_communicate, CallFragment.class);
        activityTabMain.tabContainer.addTab(communicateTab);
        //我
        settingTab = activityTabMain.tabContainer.newTabSpec(R.drawable.main_app_setting, R.string.main_tab_me, MineFragment.class);
        activityTabMain.tabContainer.addTab(settingTab);

        activityTabMain.tabContainer.setDefaultSelected(0);
        activityTabMain.tabContainer.apply(activityTabMain.tabContent, this);
        applySkin();
    }

    private void applySkin() {
        mapTab.applySkin(ThemeUtil.getDrawableSelector("main_app_list", "main_app_list")
                , ThemeUtil.getTabTextSelector());
        exploreTab.applySkin(ThemeUtil.getDrawableSelector("main_app_list", "main_app_list")
                , ThemeUtil.getTabTextSelector());
        contactTab.applySkin(ThemeUtil.getDrawableSelector("main_app_contact", "main_app_contact")
                , ThemeUtil.getTabTextSelector());
        homeTab.applySkin(ThemeUtil.getDrawableSelector("main_app_home", "main_app_home")
                , ThemeUtil.getTabTextSelector());
        communicateTab.applySkin(ThemeUtil.getDrawableSelector("main_app_communicate", "main_app_communicate")
                , ThemeUtil.getTabTextSelector());
        settingTab.applySkin(ThemeUtil.getDrawableSelector("main_app_setting", "main_app_setting")
                , ThemeUtil.getTabTextSelector());
    }

    @Override
    protected void onPermissionGranted(int requestCode) {
        activityTabMain.tabContainer.getCurrentTab().getFragment().onPermissionGranted(requestCode);
    }

    @Override
    protected void onPermissionDenied(List<String> permissions, int requestCode) {
        activityTabMain.tabContainer.getCurrentTab().getFragment().onPermissionDenied(permissions, requestCode);
    }

}
