package com.std.framework.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.core.NavigationBar;
import com.std.framework.core.ToolBarWrapper;


/**
 * Created by gfy on 2016/4/13.
 */
public class BaseTitleActivity extends BaseActivity {
    private NavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.root_layout);
        navigationBar = setNavigationBar((Toolbar) findViewById(R.id.toolbar));
        onNavigationBar(navigationBar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        if (container == null) {
            throw new NullPointerException(getClass().getSimpleName() + ":container is null");
        }
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected NavigationBar setNavigationBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        return new NavigationBar(new ToolBarWrapper(getSupportActionBar(), toolbar));
    }

    public NavigationBar getNavigationBar(){
        return navigationBar;
    }

    protected void onNavigationBar(NavigationBar navigation) {

    }

}
