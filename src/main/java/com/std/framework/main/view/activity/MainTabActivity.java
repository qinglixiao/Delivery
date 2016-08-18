package com.std.framework.main.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.ActivityTabMain;
import com.std.framework.main.view.fragment.CommunicateFragment;
import com.std.framework.main.view.fragment.ContactFragment;
import com.std.framework.main.view.fragment.MainFragment;
import com.std.framework.main.view.fragment.ListFragment;
import com.std.framework.main.view.fragment.SettingFragment;
import com.std.framework.util.ThemeUtil;
import com.std.framework.view.MainBottomView;

public class MainTabActivity extends BaseTitleActivity {
    private ActivityTabMain activityTabMain;

    private MainBottomView.TabSpec listTab;
    private MainBottomView.TabSpec contactTab;
    private MainBottomView.TabSpec homeTab;
    private MainBottomView.TabSpec communicateTab;
    private MainBottomView.TabSpec settingTab;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        View view = View.inflate(this, R.layout.activity_tab_main, null);
        activityTabMain = DataBindingUtil.bind(view);
        setContentView(view);
        initTab();
    }

    private void initTab() {
        //订单列表
        listTab = activityTabMain.tabContainer.newTabSpec(0, R.drawable.main_app_list, R.string.main_tab_list, ListFragment.class);
        activityTabMain.tabContainer.addTab(listTab);
        //通讯录
        contactTab = activityTabMain.tabContainer.newTabSpec(1, R.drawable.main_app_contact, R.string.main_tab_contact, ContactFragment.class);
        activityTabMain.tabContainer.addTab(contactTab);
        //主页
        homeTab = activityTabMain.tabContainer.newTabSpec(2, R.drawable.main_app_home, R.string.main_tab_home, MainFragment.class);
        activityTabMain.tabContainer.addTab(homeTab);
        //沟通
        communicateTab = activityTabMain.tabContainer.newTabSpec(3, R.drawable.main_app_communicate, R.string.main_tab_communicate, CommunicateFragment.class);
        activityTabMain.tabContainer.addTab(communicateTab);
        //设置
        settingTab = activityTabMain.tabContainer.newTabSpec(4, R.drawable.main_app_setting, R.string.main_tab_setting, SettingFragment.class);
        activityTabMain.tabContainer.addTab(settingTab);

        activityTabMain.tabContainer.setDefaultSelected(2);
        activityTabMain.tabContainer.apply(activityTabMain.tabContent, this);
        applySkin();
    }

    private void applySkin() {
        listTab.applySkin(ThemeUtil.getDrawableSelector("main_app_list", "main_app_list")
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
    protected void onNavigationBar(NavigationBar navigation) {
        super.onNavigationBar(navigation);
        navigation.setTitle(R.string.app_name);
        navigation.setNavigationIcon(R.drawable.icon);
    }

}
