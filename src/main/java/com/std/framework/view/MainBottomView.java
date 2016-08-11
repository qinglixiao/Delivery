package com.std.framework.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.core.Reflect;
import com.std.framework.R;
import com.std.framework.basic.App;
import com.std.framework.databinding.BottomTabLayout;
import com.std.framework.fragment.BaseFragment;

import okhttp3.Request;

/**
 * Description :
 * Author: lx
 * Create on: 2016/7/13
 * Modify by：lx
 */
public class MainBottomView extends LinearLayout {

    public MainBottomView(Context context) {
        super(context);
    }

    public TabSpec newTabSpec(int index, @DrawableRes int iconDrawableId, @StringRes int iconNameId, Class<? extends BaseFragment> clazz) {
        return new TabSpec.Builder()
                .setDrawableId(iconDrawableId)
                .setStrId(iconNameId)
                .setFragmentClass(clazz)
                .setIndex(index)
                .build();
    }

    public void addTab(TabSpec tabSpec) {
        LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER;
        addView(tabSpec.getView(),lp);
    }

    public static class TabSpec implements OnClickListener {
        private int drawableId;
        private int strId;
        private int index;
        private BottomTabLayout bottomTabLayout;
        private Class<? extends BaseFragment> fragmentClass;
        private OnTabClickListener onTabClickListener;

        private TabSpec(int index,int drawableId, int strId, Class<? extends BaseFragment> clazz) {
            this.drawableId = drawableId;
            this.strId = strId;
            this.fragmentClass = clazz;
            this.index = index;
        }

        protected View getView() {
            View view = View.inflate(App.instance, R.layout.bottom_tab_layout, null);
            BottomTabLayout bottomTabLayout = DataBindingUtil.bind(view);
            bottomTabLayout.imgTabIcon.setImageResource(drawableId);
            bottomTabLayout.tvTabName.setText(strId);
            bottomTabLayout.tabMain.setOnClickListener(this);
            return view;
        }

        public ImageView getIconImageView(){
            return bottomTabLayout.imgTabIcon;
        }

        public TextView getIconTextView(){
            return bottomTabLayout.tvTabName;
        }

        public void setOnTabClickListener(OnTabClickListener onTabClickListener){
            this.onTabClickListener = onTabClickListener;
        }

        public int getIndex(){
            return index;
        }

        public BaseFragment getFragment(){
            return Reflect.on(fragmentClass).create().get();
        }

        @Override
        public void onClick(View v) {
            if(onTabClickListener != null){
                onTabClickListener.onClick(this);
            }
        }

        interface OnTabClickListener{
            void onClick(TabSpec tabSpec);
        }

        public static class Builder {
            private int drawableId;

            private int strId;

            private int index;

            private Class<? extends BaseFragment> fragmentClass;

            public Builder setStrId(int strId) {
                this.strId = strId;
                return this;
            }

            public Builder setFragmentClass(Class<? extends BaseFragment> fragmentClass) {
                this.fragmentClass = fragmentClass;
                return this;
            }

            public Builder setDrawableId(int drawableId) {
                this.drawableId = drawableId;
                return this;
            }

            public Builder setIndex(int index){
                this.index = index;
                return this;
            }

            public TabSpec build() {
                return new TabSpec(index, drawableId, strId, fragmentClass);
            }
        }
    }

}
