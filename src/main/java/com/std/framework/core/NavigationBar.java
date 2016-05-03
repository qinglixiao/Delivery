package com.std.framework.core;

import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.View;

import com.std.framework.interfaces.OnMenuItemWrapClickListener;

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

    public void setTitle(@StringRes int resId){
        wrapper.setTitle(resId);
    }

    public void setOnMenuItemClickListener(OnMenuItemWrapClickListener listener) {
        wrapper.getToolbar().setOnMenuItemClickListener(listener);
    }

    public void setOnNavigationClickListener(View.OnClickListener listener){
        wrapper.getToolbar().setNavigationOnClickListener(listener);
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        wrapper.getToolbar().setNavigationIcon(resId);
    }

    public void setNavigationContentDescription(CharSequence txt){
        wrapper.getToolbar().setNavigationContentDescription(txt);
    }

    public void setMenu(@MenuRes int resId) {
        wrapper.getToolbar().inflateMenu(resId);
    }

    public Menu makeMenu() {
        return wrapper.getToolbar().getMenu();
    }

    public void setLogo(@DrawableRes int resId){
        wrapper.getToolbar().setLogo(resId);
    }

}
