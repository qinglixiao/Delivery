package com.std.framework.core;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by gfy on 2016/4/16.
 */
public class NavigationBar {
    private ToolBarWrapper wrapper;

    public NavigationBar(ToolBarWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void setSubTitle(CharSequence subTitle) {
        wrapper.getToolbar().setSubtitle(subTitle);
    }

    public void setTitle(CharSequence title) {
        wrapper.setTitle(title);
    }

    public void setTitle(@StringRes int resId) {
        wrapper.setTitle(resId);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        wrapper.getToolbar().setNavigationOnClickListener(listener);
    }

    public void setIcon(@DrawableRes int resId) {
        wrapper.getToolbar().setNavigationIcon(resId);
    }

    public void setDescription(CharSequence txt) {
        wrapper.getToolbar().setNavigationContentDescription(txt);
    }

    public void setLogo(@DrawableRes int resId) {
        wrapper.getToolbar().setLogo(resId);
    }

    public void setVisibility(int visibility) {
        wrapper.getToolbar().setVisibility(visibility);
    }

    public void addRightButton(String title, @DrawableRes int iconRes, Toolbar.OnMenuItemClickListener callback) {
        wrapper.addMenu(title, iconRes, callback);
    }

    public void addRightButton(String title, Toolbar.OnMenuItemClickListener callback) {
        wrapper.addMenu(title, -1, callback);
    }

    public void addRightButton(@DrawableRes int iconRes, Toolbar.OnMenuItemClickListener callback) {
        wrapper.addMenu("", iconRes, callback);
    }

    public void resetMenu() {
        wrapper.clearMenu();
    }

}
