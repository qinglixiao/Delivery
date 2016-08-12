package com.std.framework.main.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.ActivityTabMain;
import com.std.framework.main.view.fragment.MainFragment;
import com.std.framework.main.view.fragment.SecondFragment;
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
        listTab = activityTabMain.tabContainer.newTabSpec(0, R.drawable.main_app_list, R.string.main_tab_list, MainFragment.class);
        activityTabMain.tabContainer.addTab(listTab);
        //通讯录
        contactTab = activityTabMain.tabContainer.newTabSpec(1, R.drawable.main_app_contact, R.string.main_tab_contact, SecondFragment.class);
        activityTabMain.tabContainer.addTab(contactTab);
        //主页
        homeTab = activityTabMain.tabContainer.newTabSpec(2, R.drawable.main_app_home, R.string.main_tab_home, SecondFragment.class);
        activityTabMain.tabContainer.addTab(homeTab);
        //沟通
        communicateTab = activityTabMain.tabContainer.newTabSpec(3, R.drawable.main_app_communicate, R.string.main_tab_communicate, SecondFragment.class);
        activityTabMain.tabContainer.addTab(communicateTab);
        //设置
        settingTab = activityTabMain.tabContainer.newTabSpec(4, R.drawable.main_app_setting, R.string.main_tab_setting, SecondFragment.class);
        activityTabMain.tabContainer.addTab(settingTab);

        activityTabMain.tabContainer.apply(activityTabMain.tabContent,this);
    }

    @Override
    protected void onNavigationBar(NavigationBar navigation) {
        super.onNavigationBar(navigation);
        navigation.setTitle(R.string.app_name);
        navigation.setNavigationIcon(R.drawable.icon);
    }

}
