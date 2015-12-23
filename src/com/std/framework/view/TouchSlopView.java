package com.std.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.std.framework.R;

/**
 * 
 * 描          述 ：可以通过手势滑动打开关闭的控件
 * 创建日期  : 2014-12-4
 * 作           者 ： lx
 * 修改日期  : 
 * 修   改   者 ：
 * @version   : 1.0
 */
public class TouchSlopView extends RelativeLayout {
	private static final String TAG = "LX";
	private static final boolean DEBUG = false;
	private static final boolean USE_CACHE = false;
	private static final int MAX_SETTLE_DURATION = 600; // ms
	private static final int OPEN_LEFT = 0;
	private static final int OPEN_RIGHT = 1;
	private Scroller mScroller;
	private boolean mScrollingCacheEnabled;
	private int mTouchSlop;
	private float mLastMotionX;
	private float mLastMotionY;
	private float mInitialMotionX;
	private float mInitialMotionY;
	private int mActivePointId;
	private OnSlopListener mSlopListener;
	private ViewConfiguration mViewConfiguration;
	private boolean mIsBeingDragged;
	private int OPEN_MODE = OPEN_LEFT;
	private EdgeEffectCompat mLeftEdge;
	private EdgeEffectCompat mRightEdge;
	private int mScrollState;
	private Drawable mShadowDrawable;
	/**
	 *视图无操作
	 */
	public static final int SCROLL_STATE_IDLE = 0;
	/**
	 * 视图正在被拖动
	 */
	public static final int SCROLL_STATE_DRAGGING = 1;
	/**
	 * 视图已经到达最终位置
	 */
	public static final int SCROLL_STATE_SETTLING = 2;

	public TouchSlopView(Context context) {
		super(context);
		init();
	}

	public TouchSlopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public interface OnSlopListener {
		void onDraging();

		void onReset();

		void onOpend();
	}

	public void setOnSlopChangeListener(OnSlopListener onSlopChangeListener) {
		mSlopListener = onSlopChangeListener;
	}

	private final Interpolator interpolator = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			// TODO Auto-generated method stub
			t -= 1.0f;
			return t * t * t * t * t + 1.0f;
		}
	};

	void init() {
//		setWillNotDraw(false);
		mScroller = new Scroller(getContext(), interpolator);
		mViewConfiguration = ViewConfiguration.get(getContext());
		mTouchSlop = mViewConfiguration.getScaledTouchSlop();
		mLeftEdge = new EdgeEffectCompat(getContext());
		mRightEdge = new EdgeEffectCompat(getContext());
		mShadowDrawable = getContext().getResources().getDrawable(R.drawable.shadow1);
	}


//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		// TODO Auto-generated method stub
//		int width = MeasureSpec.getSize(widthMeasureSpec);
//		int height = MeasureSpec.getSize(heightMeasureSpec);
//
//		int w_mode = MeasureSpec.getMode(widthMeasureSpec);
//		int h_mode = MeasureSpec.getMode(heightMeasureSpec);
//
//		int width_temp = 0;
//		int height_temp = 0;
//		int count = getChildCount();
//		for (int i = 0; i < count; i++) {
//			View child = getChildAt(i);
//			if (child.getVisibility() != View.GONE) {
//				child.measure(widthMeasureSpec, heightMeasureSpec);
//
//				int c_heigth = child.getMeasuredHeight();
//				int c_width = child.getMeasuredWidth();
//
//				switch (w_mode) {
//					case MeasureSpec.UNSPECIFIED:
//					case MeasureSpec.AT_MOST:
//						width_temp += c_width;
//						break;
//					case MeasureSpec.EXACTLY:
//						width_temp = width;
//				}
//				switch (h_mode) {
//					case MeasureSpec.UNSPECIFIED:
//					case MeasureSpec.AT_MOST:
//						height_temp += c_heigth;
//						break;
//					case MeasureSpec.EXACTLY:
//						height_temp = height;
//				}
//			}
//
//		}
//		setMeasuredDimension(width_temp, height_temp);
//	}

//	@Override
//	protected void dispatchDraw(Canvas canvas) {
//		// TODO Auto-generated method stub
//		super.dispatchDraw(canvas);
//		if(getChildCount() == 0)
//			return;
//		if (OPEN_MODE == OPEN_LEFT) {
//			Paint paint = getShadowPaint();
//			int alpha = Math.abs(getScrollX()) * paint.getAlpha() / getClientWidth();
//			paint.setAlpha(paint.getAlpha() - alpha);
//			canvas.drawRect(new Rect(getScrollX(), 0, 0, getHeight()), paint);
//			mShadowDrawable.setBounds(new Rect(-30, 0, 0, getHeight()));
//			mShadowDrawable.draw(canvas);
//		}
//	}

	Paint getShadowPaint() {
		Paint paint = new Paint();
		Shader shader = new LinearGradient(0, 0, -getScrollX(), getClientWidth(), Color.GRAY, Color.GRAY, TileMode.CLAMP);
		paint.setShader(shader);
		return paint;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getActionMasked();
		switch (action) {
			case MotionEvent.ACTION_MOVE:
				float x = ev.getX();
				float y = ev.getY();
				float dx = Math.abs(mLastMotionX - x);
				float dy = Math.abs(mLastMotionY - y);
				Log.v(TAG, "Moved x to " + x + "," + y + " diff=" + dx + "," + dy);
				if (dx > mTouchSlop && dx > dy) {
					setScrollState(SCROLL_STATE_DRAGGING);
					mIsBeingDragged = true;
					mLastMotionX = dx > 0 ? mInitialMotionX + mTouchSlop : mInitialMotionX - mTouchSlop;
					mLastMotionY = y;
					requestDisallowInterceptTouchEvent(true);
				}
				else {
					setScrollState(SCROLL_STATE_IDLE);
					mIsBeingDragged = false;
				}
				if (mIsBeingDragged) {
					return performDrag(x);
				}
				break;
			case MotionEvent.ACTION_DOWN:
				mLastMotionX = mInitialMotionX = ev.getX();
				mLastMotionY = mInitialMotionY = ev.getY();
				mScroller.computeScrollOffset();
				break;

		}
		return mIsBeingDragged;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getActionMasked();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				if (!mScroller.isFinished())
					mScroller.abortAnimation();
				mLastMotionX = mInitialMotionX = event.getX();
				mLastMotionY = mInitialMotionY = event.getY();
				mActivePointId = MotionEventCompat.getPointerId(event, 0);
				Log.d(TAG, "mActivePointId:"+mActivePointId);
				break;
			case MotionEvent.ACTION_MOVE:
				if (!mIsBeingDragged) {
					int pointId = MotionEventCompat.findPointerIndex(event, mActivePointId);
					float x = event.getX(pointId);
					float y = event.getY(pointId);
					float dx = Math.abs(mLastMotionX - x);
					float dy = Math.abs(mLastMotionY - y);
					if (dx > mTouchSlop && dx > dy) {
						setScrollState(SCROLL_STATE_DRAGGING);
						mIsBeingDragged = true;
						mLastMotionX = x - mInitialMotionX > 0 ? mInitialMotionX + mTouchSlop : mInitialMotionX - mTouchSlop;
						mLastMotionY = y;
					}
					else {
						setScrollState(SCROLL_STATE_IDLE);
						mIsBeingDragged = false;
					}
				}
				if (mIsBeingDragged) {
					int pointId = MotionEventCompat.findPointerIndex(event, mActivePointId);
					float x = MotionEventCompat.getX(event, pointId);
					float dx = mLastMotionX - x;
					if ((dx < 0 && OPEN_MODE == OPEN_LEFT) || getScrollX() < 0) {//左滑模式
						performDrag(x);
					}
					else if ((dx > 0 && OPEN_MODE == OPEN_RIGHT) || getScrollX() > 0) {//右滑模式
						performDrag(x);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				smoothScrollTo((int) getTargetX(getScrollX()), 0);
				mIsBeingDragged = false;
				setScrollState(SCROLL_STATE_IDLE);
				Log.d(TAG, "ACTION_UP");
				break;
			case MotionEvent.ACTION_CANCEL:
				mIsBeingDragged = false;
				setScrollState(SCROLL_STATE_IDLE);
				Log.d(TAG, "ACTION_CANCEL");
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				Log.d(TAG, "ACTION_POINTER_DOWN");
				Log.d(TAG, event.getPointerCount() + "");
				int index = MotionEventCompat.getActionIndex(event);
				mLastMotionX = MotionEventCompat.getX(event, index);
				mActivePointId = MotionEventCompat.findPointerIndex(event, index);
				break;
			case MotionEvent.ACTION_POINTER_UP:
				onSecondaryPointerUp(event);
				mLastMotionX = MotionEventCompat.getX(event, MotionEventCompat.findPointerIndex(event, mActivePointId));
				break;

		}
		return true;
	}
	
	private void onSecondaryPointerUp(MotionEvent ev) {
		final int pointerIndex = MotionEventCompat.getActionIndex(ev);
		final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
		if (pointerId == mActivePointId) {
			// This was our active pointer going up. Choose a new
			// active pointer and adjust accordingly.
			final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
			mLastMotionX = MotionEventCompat.getX(ev, newPointerIndex);
			mActivePointId = MotionEventCompat.getPointerId(ev, newPointerIndex);
		}
	}


	private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
		final ViewParent parent = getParent();
		if (parent != null) {
			parent.requestDisallowInterceptTouchEvent(disallowIntercept);
		}
	}

	private float getTargetX(float scrollX) {
		float finalx = 0f;
		int quarter = getClientWidth() / 4;
		if (scrollX > 0 && scrollX <= quarter) {
			finalx = 0;
		}
		else if (scrollX > quarter) {
			finalx = getWidth();
		}
		else if (scrollX < 0 && scrollX >= -quarter) {
			finalx = 0;
		}
		else if (scrollX < -quarter) {
			finalx = -getClientWidth();
		}
		return finalx;
	}

	public void open() {
		if (OPEN_MODE == OPEN_LEFT)
			smoothScrollTo(-getClientWidth(), getScrollY());
		else if (OPEN_MODE == OPEN_RIGHT)
			smoothScrollTo(getClientWidth(), getScrollY());
	}

	private void smoothScrollTo(int x, int y) {
		int cx = getScrollX();
		int cy = getScrollY();
		int dx = x - cx;
		int dy = y - cy;
		final int width = getClientWidth();
		final int halfWidth = width / 2;
		final float distanceRatio = Math.min(1f, 1.0f * Math.abs(dx) / width);
		final float distance = halfWidth + halfWidth * distanceInfluenceForSnapDuration(distanceRatio);
		int duration = 0;
		duration = (int) ((distance / width + 4) * 100);
		duration = Math.min(duration, MAX_SETTLE_DURATION);
		mScroller.startScroll(cx, cy, dx, dy, duration);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
			int oldX = getScrollX();
			int oldY = getScrollY();
			int x = mScroller.getCurrX();
			int y = mScroller.getCurrY();
			if (oldX != x || oldY != y) {
				scrollTo(x, y);
			}
			postInvalidate();
		}
		if ((getScrollX() == getClientWidth() || getScrollX() == -getClientWidth()) && mSlopListener != null)
			mSlopListener.onOpend();
	}

	private void setScrollingCacheEnabled(boolean enabled) {
		if (mScrollingCacheEnabled != enabled) {
			mScrollingCacheEnabled = enabled;
			if (USE_CACHE) {
				final int size = getChildCount();
				for (int i = 0; i < size; ++i) {
					final View child = getChildAt(i);
					if (child.getVisibility() != GONE) {
						child.setDrawingCacheEnabled(enabled);
					}
				}
			}
		}
	}

	float distanceInfluenceForSnapDuration(float f) {
		f -= 0.5f; // center the values about 0.
		f *= 0.3f * Math.PI / 2.0f;
		return (float) Math.sin(f);
	}

	private void setScrollState(int state) {
		mScrollState = state;
	}

	private int getClientWidth() {
		return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
	}

	private boolean performDrag(float x) {
		Log.d(TAG, "move_to_x:"+x);
		if (mSlopListener != null)
			mSlopListener.onDraging();
		float dx = mLastMotionX - x;
		mLastMotionX = x;
		float oldScrollX = getScrollX();
		float scrollX = oldScrollX + dx;
		if ((OPEN_MODE == OPEN_LEFT && scrollX > 0) || (OPEN_MODE == OPEN_RIGHT && scrollX < 0)) {
			scrollX = 0;
		}
		scrollTo((int) scrollX, getScrollY());
		return true;
	}

}
