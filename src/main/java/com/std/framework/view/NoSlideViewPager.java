package com.std.framework.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description : 禁止滑动翻页viewpager,解决与scrollview水平滑动冲突事件
 * Created by lx on 2016/6/21
 * Job number：137289
 * Phone ：        18611867932
 * Email：          lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
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
