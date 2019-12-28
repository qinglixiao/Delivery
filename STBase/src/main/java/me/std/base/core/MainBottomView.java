package me.std.base.core;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.std.base.R;
import me.std.base.base.BaseFragment;
import me.std.base.base.ChunyuActivity;
import me.std.common.core.Reflect;
import me.std.common.exception.ReflectException;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/20
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class MainBottomView extends LinearLayout {

    protected List<TabSpec> tabs = new ArrayList<>();

    private TabSpec cur_tab_spec;

    private ViewPager viewPager;

    private int default_index = 0;

    private int last_selected_index = default_index;

    public MainBottomView(Context context) {
        super(context);
    }

    public MainBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabSpec newTabSpec(@DrawableRes int iconDrawableId, @StringRes int iconNameId, Class<? extends BaseFragment> clazz) {
        return new TabSpec.Builder()
                .setDrawableId(iconDrawableId)
                .setStrId(iconNameId)
                .setFragmentClass(clazz)
                .setContext(getContext())
                .build();
    }

    public void addTab(TabSpec tabSpec) {
        LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER;
        addView(tabSpec.getView(), lp);
        tabs.add(tabSpec);
        tabSpec.setIndex(tabs.size() - 1);
        tabSpec.setOnTabClickListener(tabClickListener);
    }

    public void setDefaultSelected(int index) {
        if (index < 0 || index >= tabs.size()) {
            index = 0;
        }
        default_index = index;
        last_selected_index = default_index;
        cur_tab_spec = tabs.get(default_index);
    }

    public void setTextColor(ColorStateList colors) {
        if (tabs != null) {
            for (TabSpec tabSpec : tabs) {
                tabSpec.setTextColor(colors);
            }
        }
    }

    public <T extends ChunyuActivity> void apply(ViewPager viewPager, T context) {
        this.viewPager = viewPager;
        if (viewPager != null) {
            viewPager.setAdapter(new TabAdapter(context.getSupportFragmentManager()));
            viewPager.setOffscreenPageLimit(tabs.size());
            viewPager.setCurrentItem(default_index);
            tabs.get(default_index).setSelected(true);
        }
    }

    public TabSpec getCurrentTab() {
        return cur_tab_spec;
    }

    private TabSpec.OnTabClickListener tabClickListener = new TabSpec.OnTabClickListener() {
        @Override
        public void onClick(TabSpec tabSpec) {
            if (last_selected_index == tabSpec.getIndex()) {
                return;
            }
            cur_tab_spec = tabSpec;
            tabSpec.setSelected(true);
            tabs.get(last_selected_index).setSelected(false);
            last_selected_index = tabSpec.getIndex();
            if (viewPager != null) {
                viewPager.setCurrentItem(tabSpec.getIndex(), false);
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
        private BaseFragment fragment;
        private OnTabClickListener onTabClickListener;
        private Context context;
        private View view;
        private ImageView iconView;
        private TextView titleView;

        private TabSpec(int drawableId, int strId, Class<? extends BaseFragment> clazz, Context context) {
            this.drawableId = drawableId;
            this.strId = strId;
            this.fragmentClass = clazz;
            this.context = context;
        }

        protected View getView() {
            if (view == null) {
                view = View.inflate(context, R.layout.bottom_tab_layout, null);
                iconView = view.findViewById(R.id.img_tab_icon);
                titleView = view.findViewById(R.id.tv_tab_name);
                iconView.setImageResource(drawableId);
                titleView.setText(strId);
                view.setOnClickListener(onClickListener);
            }
            return view;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
            view.setSelected(isSelected);
        }

        public void setTextColor(ColorStateList colors) {
            titleView.setTextColor(colors);
        }

        public boolean isSelected() {
            return isSelected;
        }

        public ImageView getIconImageView() {
            return iconView;
        }

        public TextView getIconTextView() {
            return titleView;
        }

        public void applySkin(Drawable iconDrawable, ColorStateList textColor) {
            getIconImageView().setImageDrawable(iconDrawable);
            getIconTextView().setTextColor(textColor);
        }

        public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
            this.onTabClickListener = onTabClickListener;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public BaseFragment getFragment() {
            if (fragment == null) {
                try {
                    fragment = Reflect.on(fragmentClass).create().get();
                } catch (ReflectException e) {
                    e.printStackTrace();
                }
            }
            return fragment;
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

            public Builder setContext(Context context) {
                this.context = context;
                return this;
            }

            public TabSpec build() {
                return new TabSpec(drawableId, strId, fragmentClass, context);
            }
        }
    }

}
