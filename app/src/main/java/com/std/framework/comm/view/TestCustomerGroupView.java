package com.std.framework.comm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.std.framework.R;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/8/31
 * Modify by：lx
 */
public class TestCustomerGroupView extends LinearLayout {
    private Paint paint;
    private int line_length = 20;

    public TestCustomerGroupView(Context context) {
        super(context);
        init();
    }

    public TestCustomerGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        paint = new Paint();
        paint.setColor(getContext().getResources().getColor(R.color.blue));
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 100, getWidth(), 100, paint);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean sp = super.drawChild(canvas, child, drawingTime);
        int right = child.getRight();
        canvas.drawLine(right, child.getTop() + child.getHeight() / 2, right + 20, child.getTop() + child.getHeight() / 2, paint);
        return sp;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(0, 100, getWidth(), 100, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        int count = getChildCount();
//        for (int i = 0; i < count; i++) {
//            int width = getChildAt(i).getMeasuredWidth();
//            int height = getChildAt(i).getMeasuredHeight();
//            getChildAt(i).layout(l, t, width + l, height);
//            l += width + line_length;
//        }
//    }
}
