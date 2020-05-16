package com.std.framework.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.std.framework.R;

/**
 * Description : 适合作步骤引导，通过getBitmap可以绘制任何中间扣洞部分
 * Author:       lx
 * Create on:  2017/3/21.
 * Modify by：lx
 */
public class GuideBackGround extends ImageView {
    private float mInnerShapeWidth;
    private float mInnerShapeHeight;
    private float mInnerShapeLeft;
    private float mInnerShapeTop;

    private Paint mPaint;

    public GuideBackGround(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public GuideBackGround(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray attribute = context.obtainStyledAttributes(attrs, R.styleable.GuideBackGround);
        mInnerShapeWidth = attribute.getDimension(R.styleable.GuideBackGround_InnerShapeWidth, 0);
        mInnerShapeHeight = attribute.getDimension(R.styleable.GuideBackGround_InnerShapeHeight, 0);
        mInnerShapeLeft = attribute.getDimension(R.styleable.GuideBackGround_InnerShapeLeft, 0);
        mInnerShapeTop = attribute.getDimension(R.styleable.GuideBackGround_InnerShapeTop, 0);
        attribute.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int i = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
            Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                Canvas layerCanvas = new Canvas(bitmap);
                drawable.draw(layerCanvas);
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                Bitmap maskBitmap = getBitmap();
                if (maskBitmap != null) {
                    if (mInnerShapeLeft == 0) {
                        mInnerShapeLeft = (getWidth() - mInnerShapeWidth) / 2;
                    }
                    layerCanvas.drawBitmap(maskBitmap, mInnerShapeLeft, mInnerShapeTop, mPaint);
                }
            }
            if (bitmap != null) {
                mPaint.setXfermode(null);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
                return;
            }
            canvas.restoreToCount(i);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap((int) mInnerShapeWidth, (int) mInnerShapeHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mPaint.setColor(Color.BLACK);
        canvas.drawOval(new RectF(0, 0, mInnerShapeWidth, mInnerShapeHeight), mPaint);
        return bitmap;
    }
}
