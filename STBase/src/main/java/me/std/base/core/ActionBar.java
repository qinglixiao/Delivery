package me.std.base.core;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.std.base.R;
import me.std.common.utils.AppContextUtil;
import me.std.common.utils.Logger;
import me.std.common.utils.ViewUtil;

/**
 * 导航栏func
 */
public class ActionBar {
    private static final int ORIGIN = 0x0001;
    /**
     * 返回按钮可见性
     */
    public static final int FLAG_VISUAL_BACK = ORIGIN;
    /**
     * 返回关闭按钮可见性
     */
    public static final int FLAG_VISUAL_CLOSE = ORIGIN << 1;
    /**
     * 右一按钮可见性
     */
    public static final int FLAG_VISUAL_RIGHT_BUTTON1 = ORIGIN << 2;
    /**
     * 进度条可见性
     */
    public static final int FLAG_VISUAL_PROGRESS = ORIGIN << 3;
    /**
     * 整个导航栏可见性
     */
    public static final int FLAG_VISUAL_BAR = ORIGIN << 4;

    private int flagMark;
    private View vHeader;
    private TextView vTitle;
    private TextView vBack;
    private TextView vRightButton1;
    private ImageView vClose;
    private ProgressBar vProgress;
    private View vContainer;

    private ActionBar(Context context) {
        vHeader = LayoutInflater.from(context).inflate(R.layout.action_bar, null);
        vContainer = vHeader.findViewById(R.id.ll_parent);
        vTitle = vHeader.findViewById(R.id.tv_title);
        vBack = vHeader.findViewById(R.id.tv_back);
        vRightButton1 = vHeader.findViewById(R.id.tv_right_button_1);
        vClose = vHeader.findViewById(R.id.tv_close);
        vProgress = vHeader.findViewById(R.id.bar_progress);
        applySkin();
        defaultSetting();
    }

    private void defaultSetting() {
        //默认显示导航栏、返回按钮
        flagMark = FLAG_VISUAL_BACK;
        flagMark |= FLAG_VISUAL_BAR;

        clearValue();
        vContainer.setBackgroundColor(Color.parseColor("#fdfdfd"));
    }

    private void clearValue() {
        vTitle.setText("");
        vBack.setText("");
        vBack.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    private void applySkin() {
        try {
            Resources res = AppContextUtil.getAppContext().getResources();
            int c_title = res.getIdentifier("c_action_bar_title", "color", AppContextUtil.getAppContext().getPackageName());
            int d_back = res.getIdentifier("d_action_bar_back_icon", "drawable", AppContextUtil.getAppContext().getPackageName());
            int d_close = res.getIdentifier("d_action_bar_close", "drawable", AppContextUtil.getAppContext().getPackageName());
            int c_right_button = res.getIdentifier("c_action_bar_right_button", "color", AppContextUtil.getAppContext().getPackageName());
            vTitle.setTextColor(res.getColor(c_title));
            vRightButton1.setTextColor(res.getColor(c_right_button));
            vBack.setCompoundDrawablesWithIntrinsicBounds(res.getDrawable(d_back), null, null, null);

        } catch (Exception ex) {
            Logger.e("lx", ex.getMessage());
        }

    }

    private void refresh() {
        ViewUtil.show(vBack, (FLAG_VISUAL_BACK & flagMark) != 0);
        ViewUtil.show(vRightButton1, (FLAG_VISUAL_RIGHT_BUTTON1 & flagMark) != 0);
        ViewUtil.show(vClose, (FLAG_VISUAL_CLOSE & flagMark) != 0);
        ViewUtil.show(vProgress, (FLAG_VISUAL_PROGRESS & flagMark) != 0);
        ViewUtil.show(vHeader, (FLAG_VISUAL_BAR & flagMark) != 0);
    }

    public void setTitle(CharSequence title) {
        vTitle.setText(title);
    }

    public void setTitle(@StringRes int resId) {
        vTitle.setText(resId);
    }

    public TextView getTitle() {
        return vTitle;
    }

    public void setBackIcon(Drawable leftDrawable) {
        vBack.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null);
    }

    public void setBackIcon(@DrawableRes int resId) {
        vBack.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
    }

    public void setBackText(String text) {
        vBack.setText(text);
    }

    public void setBackText(String text, View.OnClickListener onClickListener) {
        vBack.setText(text);
        vBack.setOnClickListener(onClickListener);
    }

    public void setBackClickListener(View.OnClickListener onClickListener) {
        vBack.setOnClickListener(onClickListener);
    }

    public void setCloseIcon(Drawable src) {
        vClose.setImageDrawable(src);
    }

    public void setCloseListener(View.OnClickListener onClickListener) {
        vClose.setOnClickListener(onClickListener);
    }

    public void setRightButton1(String text, View.OnClickListener onClickListener) {
        vRightButton1.setText(text);
        vRightButton1.setOnClickListener(onClickListener);
    }

    public void setRightButton1(Drawable icon, View.OnClickListener onClickListener) {
        vRightButton1.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
        vRightButton1.setOnClickListener(onClickListener);
    }

    public void setRightButton1(@DrawableRes int icon, View.OnClickListener onClickListener) {
        vRightButton1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, icon, 0);
        vRightButton1.setOnClickListener(onClickListener);
    }

    public void show(int flag) {
        this.flagMark |= flag;
        refresh();
    }

    public void gone(int flag) {
        if ((flagMark & flag) != 0) {
            flagMark ^= flag;
        }
        refresh();
    }

    public void setProgress(int progress) {
        vProgress.setProgress(progress);
    }

    public TextView getBackButton() {
        return vBack;
    }


    public TextView getRightButton1() {
        return vRightButton1;
    }

    public ImageView getCloseButton() {
        return vClose;
    }

    public void reset() {
        defaultSetting();
    }

    public void hide() {
        ViewUtil.gone(vHeader);
    }

    public View getHeader() {
        return vHeader;
    }

    public void setActionBarColor(int color) {
        vContainer.setBackgroundColor(color);
    }

    public static class Builder {
        private ActionBar actionBar;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            actionBar = new ActionBar(context);
        }

        /**
         * 标题设置
         *
         * @param resId
         * @return
         */
        public Builder setTitle(@StringRes int resId) {
            return setTitle(context.getResources().getString(resId));
        }

        /**
         * 标题设置
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            actionBar.setTitle(title);
            return this;
        }

        /**
         * 将导航栏某个元素设为可见
         *
         * @param flag {@link #FLAG_VISUAL_BACK,#FLAG_VISUAL_BAR,...}
         * @return
         */
        public Builder show(int flag) {
            actionBar.show(flag);
            return this;
        }

        /**
         * 隐藏导航栏某个元素
         *
         * @param flag {@link #FLAG_VISUAL_BACK,#FLAG_VISUAL_BAR,...}
         * @return
         */
        public Builder gone(int flag) {
            actionBar.gone(flag);
            return this;
        }

        /**
         * 设置返回按钮icon
         * 注：返回按钮文字将不显示
         *
         * @param resId
         * @return
         */
        public Builder setBackIcon(@DrawableRes int resId) {
            actionBar.setBackText("");
            actionBar.setBackIcon(resId);
            return this;
        }

        /**
         * 设置返回按钮文字
         * 注：按钮icon将不显示
         *
         * @param resId
         * @return
         */
        public Builder setBackText(@StringRes int resId) {
            actionBar.setBackIcon(null);
            actionBar.setBackText(context.getResources().getString(resId));
            return this;
        }

        /**
         * 设置返回按钮文字
         * 注：按钮icon将不显示
         *
         * @param text
         * @return
         */
        public Builder setBackText(String text) {
            actionBar.setBackIcon(null);
            actionBar.setBackText(text);
            return this;
        }

        /**
         * 设置返回按钮文字
         * 注：按钮icon将不显示
         *
         * @param text
         * @param onClickListener
         * @return
         */
        public Builder setBackText(String text, View.OnClickListener onClickListener) {
            actionBar.setBackIcon(null);
            actionBar.setBackText(text, onClickListener);
            return this;
        }

        /**
         * 设置返回按钮 icon+文字
         *
         * @param resId
         * @param text
         * @param onClickListener
         * @return
         */
        public Builder setBackText(@DrawableRes int resId, String text, View.OnClickListener onClickListener) {
            actionBar.setBackIcon(resId);
            actionBar.setBackText(text);
            actionBar.setBackClickListener(onClickListener);
            return this;
        }

        /**
         * 设置返回按钮点击事件
         *
         * @param onClickListener
         * @return
         */
        public Builder setBackClickListener(View.OnClickListener onClickListener) {
            actionBar.setBackClickListener(onClickListener);
            return this;
        }

        public Builder setCloseIcon(@DrawableRes int resId) {
            actionBar.setCloseIcon(context.getResources().getDrawable(resId));
            return this;
        }

        /**
         * 设置最右侧按钮文字
         *
         * @param text
         * @param onClickListener
         * @return
         */
        public Builder setRightButton1(String text, View.OnClickListener onClickListener) {
            actionBar.setRightButton1(text, onClickListener);
            return this;
        }

        /**
         * 设置最右侧按钮icon
         *
         * @param resIcon
         * @param onClickListener
         * @return
         */
        public Builder setRightButton1(@DrawableRes int resIcon, View.OnClickListener onClickListener) {
            actionBar.setRightButton1(resIcon, onClickListener);
            return this;
        }

        public Builder setTransparent() {
            actionBar.setActionBarColor(context.getResources().getColor(R.color.transparent));
            return this;
        }

        public ActionBar build() {
            return actionBar;
        }
    }

}
