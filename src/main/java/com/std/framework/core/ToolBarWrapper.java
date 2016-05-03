package com.std.framework.core;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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

    public void setTitle(@StringRes int resId){
        mTitle.setText(resId);
    }

    public void setTitle(CharSequence title){
        mTitle.setText(title);
    }

}
