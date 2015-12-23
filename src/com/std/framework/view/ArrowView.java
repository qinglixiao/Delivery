package com.std.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ArrowView extends View {
	private Paint backgroundPaint;
	private Paint linePaint;

	public ArrowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.GRAY);
		linePaint = new Paint();
		linePaint.setColor(Color.RED);
		linePaint.setStrokeWidth(2);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		int px = getMeasuredWidth();
		int py = getMeasuredWidth();
		 
		// Draw background
		canvas.drawRect(0, 0, px, py, backgroundPaint);
		 
		canvas.save();
		canvas.rotate(90, px/2, py/2);                
		 
		// Draw up arrow
		canvas.drawLine(px / 2, 0, 0, py / 2, linePaint);                
		canvas.drawLine(px / 2, 0, px, py / 2, linePaint);
		canvas.drawLine(px / 2, 0, px / 2, py, linePaint);
		 
		canvas.restore();
		 
		// Draw circle
		canvas.drawCircle(px - 10, py - 10, 10, linePaint);
	}

}
