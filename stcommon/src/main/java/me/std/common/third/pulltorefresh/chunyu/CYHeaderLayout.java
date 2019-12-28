package me.std.common.third.pulltorefresh.chunyu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import me.std.common.R;
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
public class CYHeaderLayout extends LoadingLayout {


    public CYHeaderLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        mPullLabel = "下拉刷新数据";
        mReleaseLabel = "松开刷新数据";
        mRefreshingLabel = "正在刷新";
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    protected void onPullImpl(float scaleOfLayout) {
    }

    @Override
    protected void refreshingImpl() {
    }

    @Override
    protected void resetImpl() {
    }

    private void resetImageRotation() {
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.progress_pull_down_content;
    }
}
