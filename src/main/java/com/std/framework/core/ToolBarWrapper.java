package com.std.framework.core;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

/**
 * Created by gfy on 2016/4/25.
 */
public class ToolBarWrapper {
    private Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ToolBarWrapper(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void setTitle(@StringRes int resId){
        toolbar.setTitle(resId);
    }

    public void setTitle(CharSequence title){
        toolbar.setTitle(title);
    }

}
