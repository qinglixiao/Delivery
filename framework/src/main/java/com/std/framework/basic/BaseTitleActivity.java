package com.std.framework.basic;

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
public abstract class BaseTitleActivity extends BaseActivity {
    private NavigationBar navigationBar;

    protected void setNavigationBar(Toolbar toolbar) {
        navigationBar = new NavigationBar(new ToolBarWrapper(toolbar));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.root_layout);
        setNavigationBar((Toolbar) findViewById(R.id.toolbar));
        onNavigationBar(navigationBar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        ViewGroup container = (ViewGroup) findViewById(R.id.parent);
        if (container == null) {
            throw new NullPointerException(getClass().getSimpleName() + ":container is null");
        }
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    /**
     * 定义title
     * @param navigation
     */
    public abstract void onNavigationBar(NavigationBar navigation);

}
