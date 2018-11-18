package com.std.framework.basic;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.comm.interfaces.INavRebuild;
import com.std.framework.core.NavigationBar;

/**
 * Created by gfy on 2016/4/13.
 */
public abstract class BaseTitleActivity extends BaseActivity implements INavRebuild, View.OnClickListener {

    private NavigationBar.Builder navBuilder;

    /**
     * 自定义标题栏，重写此方法，onNavigationBar将不再起作用
     *
     * @return
     */
    protected View createNavigationBar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.root_layout);
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

        initNavigationBar();
        View navBar = createNavigationBar();
        if (navBar == null) {
            navBar = navBuilder.build().getHeader();
        }
        ((ViewGroup) container.findViewById(R.id.v_app_navigation)).addView(navBar);
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        onNavigationBar(navBuilder);
    }

    private void initNavigationBar() {
        navBuilder = new NavigationBar.Builder(this);
        NavigationBar navigationBar = navBuilder.build();
        navigationBar.getBackButton().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == navBuilder.build().getBackButton()) {//返回
            onBackPressed();
            return;
        }
    }

    public NavigationBar.Builder getNavBuilder() {
        return navBuilder;
    }

    /**
     * 定义导航栏
     *
     * @param navBuilder
     */
    protected abstract void onNavigationBar(NavigationBar.Builder navBuilder);

}
