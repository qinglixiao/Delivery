package com.std.framework.comm.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/27.
 * Modify by：lx
 */
public class FixGridLayoutManager extends GridLayoutManager {
    public FixGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int height = 0;
        int childCount = getItemCount();
        for (int i = 0; i < childCount; i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            if (i % getSpanCount() == 0) {
                int measuredHeight = child.getMeasuredHeight() + getDecoratedBottom(child) + lp.topMargin + lp.bottomMargin;
                height += measuredHeight;
            }
        }
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height + getPaddingBottom() + getPaddingTop());
    }
}
