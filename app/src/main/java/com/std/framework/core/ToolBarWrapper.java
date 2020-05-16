package com.std.framework.core;

import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;

import com.std.framework.R;

/**
 * Created by gfy on 2016/4/25.
 */
public class ToolBarWrapper {
    private Toolbar toolbar;
    private TextView mTitle;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ToolBarWrapper(Toolbar toolbar) {
        this.toolbar = toolbar;
        mTitle = (TextView) toolbar.findViewById(R.id.tv_title);
    }

    public void setTitle(@StringRes int resId) {
        mTitle.setText(resId);
    }

    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public void addMenu(String title, @DrawableRes int iconRes, Toolbar.OnMenuItemClickListener callback) {
        MenuItem menuItem = toolbar.getMenu().add(title);
        if (iconRes != -1) {
            menuItem.setIcon(iconRes);
        }
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        if (callback != null) {
            toolbar.setOnMenuItemClickListener(callback);
        }
    }

    public void clearMenu() {
        toolbar.getMenu().clear();
    }

    public void removeMenu(int menuId) {
        toolbar.getMenu().removeItem(menuId);
    }

}
