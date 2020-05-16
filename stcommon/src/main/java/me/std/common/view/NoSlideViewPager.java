package me.std.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
  * Description:
  * Author: lixiao
  * Create on: 2018/11/20
  * Job number: 1800838
  * Phone: 18611867932
  * Email: lixiao@chunyu.me
  */
public class NoSlideViewPager extends ViewPager {
	private boolean isPagingEnabled = false;

	public NoSlideViewPager(Context context) {
		super(context);
	}

	public NoSlideViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onTouchEvent(event);
	}

}
