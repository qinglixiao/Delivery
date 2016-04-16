package com.std.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class ImageTextView extends Button {
	
	public ImageTextView(Context context) {
		super(context);
		setWidth();
		// TODO Auto-generated constructor stub
	}
	
	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWidth();
		// TODO Auto-generated constructor stub
	}

	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setWidth();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	
	private void setWidth(){
//		setCompoundDrawablesWithIntrinsicBounds(0, 0, 600,600);
	}
	
	
	

}
