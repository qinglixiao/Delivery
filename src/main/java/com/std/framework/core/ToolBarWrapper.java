package com.std.framework.core;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

/**
 * Created by gfy on 2016/4/25.
 */
public class ToolBarWrapper {
    private Toolbar toolbar;
    private ActionBar actionBar;

    public ActionBar getActionBar() {
        return actionBar;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ToolBarWrapper(ActionBar actionBar, Toolbar toolbar) {
        this.actionBar = actionBar;
        this.toolbar = toolbar;
    }

}
