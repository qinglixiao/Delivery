package me.std.common.third.pulltorefresh.chunyu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import me.std.common.third.pulltorefresh.PullToRefreshBase;
import me.std.common.third.pulltorefresh.internal.LoadingLayout;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/24.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYFooterLayout extends LoadingLayout {

    public CYFooterLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    @Override
    protected int getDefaultDrawableResId() {
        return 0;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {

    }
}
