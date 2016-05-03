package com.std.framework.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.std.framework.R;
import com.std.framework.core.NavigationBar;
import com.std.framework.interfaces.OnMenuItemWrapClickListener;
import com.std.framework.view.MToast;

/**
 * Created by gfy on 2016/4/27.
 */
public class ToolBarDemoActivity extends BaseTitleActivity implements OnMenuItemWrapClickListener,View.OnClickListener {

    @Override
    protected void onNavigationBar(NavigationBar navigation) {
        navigation.setTitle("Toolbar-Demo");
//        navigation.setNavigationIcon(R.drawable.btn_left_menu);
        navigation.setLogo(R.drawable.btn_left_menu);
        navigation.setNavigationContentDescription("返回");
        navigation.setOnMenuItemClickListener(this);
        navigation.makeMenu().add("你好").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        navigation.makeMenu().add("hello").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        navigation.setOnNavigationClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        MToast.makeText(this,item.getTitle(),MToast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        MToast.makeText(this,"home", MToast.LENGTH_SHORT).show();
    }
}
