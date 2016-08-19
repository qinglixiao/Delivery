package com.std.framework.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.core.Reflect;
import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.databinding.BottomTabLayout;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.view.MainBottomView.TabSpec.OnTabClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author: lx
 * Create on: 2016/7/13
 * Modify by：lx
 */
public class MainBottomView extends LinearLayout{
    private BaseTitleActivity context;

    protected List<TabSpec> tabs = new ArrayList();

    private ViewPager viewPager;

    private int default_index = 0;

    private int last_selected_index = default_index;

    public MainBottomView(Context context) {
        super(context);
    }

    public MainBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabSpec newTabSpec(int index, @DrawableRes int iconDrawableId, @StringRes int iconNameId, Class<? extends BaseFragment> clazz) {
        return new TabSpec.Builder()
                .setDrawableId(iconDrawableId)
                .setStrId(iconNameId)
                .setFragmentClass(clazz)
                .setIndex(index)
                .setContext(getContext())
                .build();
    }

    public void addTab(TabSpec tabSpec) {
        LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER;
        addView(tabSpec.getView(), lp);
        tabs.add(tabSpec);
        tabSpec.setOnTabClickListener(tabClickListener);
    }

    public void setDefaultSelected(int index) {
        default_index = index;
        last_selected_index = default_index;
    }

    public <T extends BaseTitleActivity> void apply(ViewPager viewPager, T context) {
        this.viewPager = viewPager;
        this.context = context;
        if (viewPager != null) {
            viewPager.setAdapter(new TabAdapter(context.getSupportFragmentManager()));
            viewPager.setOffscreenPageLimit(0);
            viewPager.setCurrentItem(default_index);
            tabs.get(default_index).setSelected(true);
        }
    }

    private OnTabClickListener tabClickListener = new OnTabClickListener() {
        @Override
        public void onClick(TabSpec tabSpec) {
            tabSpec.setSelected(true);
            tabs.get(last_selected_index).setSelected(false);
            last_selected_index = tabSpec.getIndex();
            if(viewPager != null){
                viewPager.setCurrentItem(tabSpec.getIndex(),false);
            }
        }
    };

    public class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabs.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

    }

    public static class TabSpec {
        private int drawableId;
        private int strId;
        private int index;
        private boolean isSelected;
        private Class<? extends BaseFragment> fragmentClass;
        private OnTabClickListener onTabClickListener;
        private Context context;
        private BottomTabLayout bottomTabLayout;
        private View view;

        private TabSpec(int index, int drawableId, int strId, Class<? extends BaseFragment> clazz, Context context) {
            this.drawableId = drawableId;
            this.strId = strId;
            this.fragmentClass = clazz;
            this.index = index;
            this.context = context;
        }

        protected View getView() {
            if(view == null){
                view = View.inflate(context, R.layout.bottom_tab_layout, null);
                bottomTabLayout = DataBindingUtil.bind(view);
                bottomTabLayout.imgTabIcon.setImageResource(drawableId);
                bottomTabLayout.tvTabName.setText(strId);
                bottomTabLayout.tabMain.setOnClickListener(onClickListener);
            }
            return view;
        }

        public void setSelected(boolean isSelected){
            this.isSelected = isSelected;
            view.setSelected(isSelected);
        }

        public boolean isSelected(){
            return isSelected;
        }

        public ImageView getIconImageView() {
            return bottomTabLayout.imgTabIcon;
        }

        public TextView getIconTextView() {
            return bottomTabLayout.tvTabName;
        }

        public void applySkin(Drawable iconDrawable, ColorStateList textColor){
            getIconImageView().setImageDrawable(iconDrawable);
            getIconTextView().setTextColor(textColor);
        }

        public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
            this.onTabClickListener = onTabClickListener;
        }

        public int getIndex() {
            return index;
        }

        public BaseFragment getFragment() {
            return Reflect.on(fragmentClass).create().get();
        }

        private OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabClickListener != null) {
                    onTabClickListener.onClick(TabSpec.this);
                }
            }
        };

        interface OnTabClickListener {
            void onClick(TabSpec tabSpec);
        }

        public static class Builder {
            private int drawableId;

            private int strId;

            private int index;

            private Context context;

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

            public Builder setIndex(int index) {
                this.index = index;
                return this;
            }

            public Builder setContext(Context context) {
                this.context = context;
                return this;
            }

            public TabSpec build() {
                return new TabSpec(index, drawableId, strId, fragmentClass, context);
            }
        }
    }

}
