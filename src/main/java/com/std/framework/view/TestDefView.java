package com.std.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * 描          述 ：自定义view,用于测试
 * 创建日期  : 2014-12-4
 * 作           者 ： lx
 * 修改日期  : 
 * 修   改   者 ：
 * @version   : 1.0
 */
public class TestDefView extends View {
	
	public TestDefView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TestDefView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(3);
//		canvas.drawOval(new RectF(10, 10, 100, 100), paint);
		canvas.drawLines(new float[]{10,10,500,500}, paint);
	}

}
