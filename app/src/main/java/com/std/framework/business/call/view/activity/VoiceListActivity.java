package com.std.framework.business.call.view.activity;

import android.view.MenuItem;

import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.comm.interfaces.OnMenuItemWrapClickListener;
import com.std.framework.core.NavigationBar;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/12/22.
 * Modify by：lx
 */
public class VoiceListActivity extends BaseTitleActivity {

    @Override
    protected void onNavigationBar(NavigationBar.Builder navBuilder) {
        navBuilder.setTitle("语音选择");
//        navBuilder.addRightButton("确定", new OnMenuItemWrapClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
    }
}
