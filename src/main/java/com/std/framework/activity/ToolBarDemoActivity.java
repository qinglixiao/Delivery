package com.std.framework.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.std.framework.R;
import com.std.framework.core.NavigationBar;
import com.std.framework.interfaces.OnMenuItemWrapClickListener;

/**
 * Created by gfy on 2016/4/27.
 */
public class ToolBarDemoActivity extends BaseTitleActivity implements OnMenuItemWrapClickListener {

    @Override
    protected void onNavigationBar(NavigationBar navigation) {
        navigation.setNavigationIcon(R.drawable.card_icon_activity_normal);
        navigation.setOnMenuItemClickListener(this);
        navigation.makeMenu().add("你好").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        navigation.setLogo(R.drawable.btn_left_menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }
}
