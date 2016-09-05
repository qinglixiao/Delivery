package com.std.framework.comm.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ShapeView extends ImageView {

	public ShapeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStrokeWidth(0);
		paint.setColor(Color.TRANSPARENT);
		PointF[] pointF = getPolygonPoints(80, 5,90);
		Path path = new Path();
		path.moveTo(pointF[0].x, pointF[0].y);
		for(int i = 0;i < pointF.length;i++){
			path.lineTo(pointF[i].x, pointF[i].y);
		}
//		canvas.drawPath(path, paint);
		canvas.clipPath(path);
		if(getDrawable() != null)
			getDrawable().draw(canvas);
	}

	private PointF[] getPolygonPoints(int radius, int sides, float offsetAngle) {
		PointF[] points = new PointF[sides];
		int width = getWidth();
		int height = getHeight();
		offsetAngle =  (float) (Math.PI * offsetAngle/180);
		for (int i = 0; i < sides; ++i) {
			float x = (float) (width / 2 + radius * Math.cos(offsetAngle));
			float y = (float) (height / 2 - radius * Math.sin(offsetAngle));
			points[i] = new PointF(x, y);
			offsetAngle += 2 * Math.PI / sides;
		}
		return points;
	}

}
