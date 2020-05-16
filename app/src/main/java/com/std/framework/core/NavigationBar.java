package com.std.framework.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;

import com.std.framework.R;
import com.std.framework.comm.interfaces.INavigation;
import com.std.framework.util.AppUtil;
import com.std.framework.util.ViewUtil;

/**
 * Created by gfy on 2016/4/16.
 */
public class NavigationBar {
    private View vHeader;
    private TextView vTitle;
    private TextView vBack;
    private TextView vRightButton1;

    private NavigationBar(Context context) {
        vHeader = LayoutInflater.from(context).inflate(R.layout.navigation_bar, null);
        vTitle = vHeader.findViewById(R.id.tv_title);
        vBack = vHeader.findViewById(R.id.tv_back);
        vRightButton1 = vHeader.findViewById(R.id.tv_right_button_1);
        defaultSetting();
    }

    private void defaultSetting() {
        vBack.setVisibility(View.GONE);
    }

    public void setTitle(CharSequence title) {
        ViewUtil.visible(vTitle);
        vTitle.setText(title);
    }

    public void setBackIcon(Drawable leftDrawable) {
        ViewUtil.visible(vBack);
        vBack.setCompoundDrawables(leftDrawable, null, null, null);
    }

    public void setBackText(String text) {
        ViewUtil.visible(vBack);
        vBack.setText(text);
    }

    public void setBackText(String text, View.OnClickListener onClickListener) {
        ViewUtil.visible(vBack);
        vBack.setText(text);
        vBack.setOnClickListener(onClickListener);
    }

    public void setOnClickListener(View.OnClickListener listener) {

    }

    public void setIcon(@DrawableRes int resId) {
    }

    public void setDescription(CharSequence txt) {
    }

    public void setLogo(@DrawableRes int resId) {
    }

    public void setVisibility(int visibility) {
    }

    public void addRightButton(String title, @DrawableRes int iconRes, Toolbar.OnMenuItemClickListener callback) {
    }

    public void addRightButton(String title, Toolbar.OnMenuItemClickListener callback) {
    }

    public void addRightButton(@DrawableRes int iconRes, Toolbar.OnMenuItemClickListener callback) {
    }

    public TextView getBackButton() {
        return vBack;
    }

    public void reset() {
        defaultSetting();
    }

    public View getHeader() {
        return vHeader;
    }

    public static class Builder {
        private NavigationBar navigationBar;
        private String title;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            navigationBar = new NavigationBar(context);
        }

        public Builder setTitle(@StringRes int resId) {
            return setTitle(context.getResources().getString(resId));
        }

        public Builder setTitle(String title) {
            this.title = title;
            navigationBar.setTitle(title);
            return this;
        }

        public Builder setBackIcon(@DrawableRes int resId) {
            navigationBar.setBackIcon(context.getResources().getDrawable(resId));
            return this;
        }

        public Builder setBackText(@StringRes int resId) {
            navigationBar.setBackText(context.getResources().getString(resId));
            return this;
        }

        public Builder setBackText(String text) {
            navigationBar.setBackText(text);
            return this;
        }

        public Builder setBackText(String text, View.OnClickListener onClickListener) {
            navigationBar.setBackText(text, onClickListener);
            return this;
        }

        public String getTitle() {
            return title;
        }

        public NavigationBar build() {
            return navigationBar;
        }
    }

}
