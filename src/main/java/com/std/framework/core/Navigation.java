package com.std.framework.core;

import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.activity.App;

/**
 * Created by gfy on 2016/4/16.
 */
public class Navigation {
    private ActionBar bar;

    /**标题*/
    private View mTitleView;
    /**标题是否居中显示*/
    private boolean isTitleCenter = false;

    public Navigation(ActionBar actionBar){
        bar = actionBar;
        //		ImageView imageView = (ImageView) findViewById(android.R.id.home);
//		imageView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(android.widget.FrameLayout.LayoutParams.WRAP_CONTENT,
//				android.widget.FrameLayout.LayoutParams.MATCH_PARENT));
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);
    }

    public void displayActionBar(boolean isShow) {
        // TODO Auto-generated method stub
        if (isShow)
            bar.show();
        else
            bar.hide();
    }

    public void displayActionBarCustomLayout(boolean isShow) {
        // TODO Auto-generated method stub
        bar.setDisplayShowCustomEnabled(isShow);
    }

    public void displayHomeIcon(boolean isShow) {
        // TODO Auto-generated method stub
        bar.setDisplayShowHomeEnabled(isShow);
    }

    public void displayTitle(boolean isShow) {
        // TODO Auto-generated method stub
        bar.setDisplayShowTitleEnabled(isShow);
    }

    public void displayHomeAsUp(boolean isShow) {
        // TODO Auto-generated method stub
        bar.setDisplayHomeAsUpEnabled(isShow);
    }

    public void displayUseLogo(boolean enable) {
        // TODO Auto-generated method stub
        bar.setDisplayUseLogoEnabled(enable);
    }

    public void setSubTitle(CharSequence subTitle) {
        // TODO Auto-generated method stub
        bar.setSubtitle(subTitle);
    }

    public void setActionBarCustomView(View view) {
        // TODO Auto-generated method stub
        bar.setCustomView(view);
    }

    public void setActionBarCustomView(int layoutId) {
        // TODO Auto-generated method stub
        bar.setCustomView(layoutId);
    }

    public void setActionBarCustomView(View view, ActionBar.LayoutParams params) {
        // TODO Auto-generated method stub
        bar.setCustomView(view, params);
    }

    public void setHomeButtonEnabled(boolean enable) {
        // TODO Auto-generated method stub
        bar.setHomeButtonEnabled(enable);
    }

    public void requestWindowTitleCenter(boolean isCenter) {
        // TODO Auto-generated method stub
        isTitleCenter = isCenter;
        if (isCenter) {
            if (mTitleView == null) {
                mTitleView = View.inflate(App.instance,R.layout.custom_title, null);
            }
            android.support.v7.app.ActionBar.LayoutParams params = new android.support.v7.app.ActionBar.LayoutParams(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            setActionBarCustomView(mTitleView, params);
            displayActionBarCustomLayout(true);
            displayTitle(false);
        }
        else {
            displayActionBarCustomLayout(false);
            displayTitle(true);
        }
    }

    public void setTitle(CharSequence title) {
        // TODO Auto-generated method stub
        if (isTitleCenter)
            ((TextView) mTitleView.findViewById(R.id.tv_title_custom)).setText(title);
        else
            bar.setTitle(title);
    }

}
