package com.std.framework.widget.dialog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.std.framework.widget.dialog.WheelView.NumericWheelAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.Time;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class DatePicker extends LinearLayout {
	private final String[] MONTHS_BIG = { "1", "3", "5", "7", "8", "10", "12" };// 大月
	private final String[] MONTHS_LITTLE = { "4", "6", "9", "11" };// 小月

	private Context mContext;
	private WheelView mYearWheel;
	private WheelView mMonthWheel;
	private WheelView mDayWheel;
	private NumericWheelAdapter mYearAdapter;
	private NumericWheelAdapter mMonthAdapter;
	private NumericWheelAdapter mDayAdapter;

	private final List<String> list_big = Arrays.asList(MONTHS_BIG);
	private final List<String> list_little = Arrays.asList(MONTHS_LITTLE);

	public DatePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		mContext = context;
		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		initView(context, attrs);
		adapterScreen();
		initAdapter();
		initListener();
		setDefaultDate();
	}

	private void initView(Context context, AttributeSet attrs) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		params.setMargins(2, 2, 2, 2);
		mYearWheel = new WheelView(context, attrs);
		mMonthWheel = new WheelView(context, attrs);
		mDayWheel = new WheelView(context, attrs);
		mYearWheel.setVisibleItems(5);
		mMonthWheel.setVisibleItems(5);
		mDayWheel.setVisibleItems(5);
		// mYearWheel.setLabel("年");
		mMonthWheel.setLabel("月");
		mDayWheel.setLabel("日");
		addView(mYearWheel, params);
		addView(mMonthWheel, params);
		addView(mDayWheel, params);
	}

	private void setDefaultDate() {
		setDate(Calendar.getInstance());
	}

	private void initAdapter() {
		Time time = new Time();
		time.setToNow();
		mYearAdapter = mYearWheel.new NumericWheelAdapter(time.year - 50, time.year + 50);
		mMonthAdapter = mMonthWheel.new NumericWheelAdapter(1, 12);
		mYearWheel.setAdapter(mYearAdapter);
		mMonthWheel.setAdapter(mMonthAdapter);
	}

	public void setDate(int year, int month, int day) {
		int year_index = year - mYearAdapter.getMin();
		if (year_index >= 0)
			mYearWheel.setCurrentItem(year_index);
		mMonthWheel.setCurrentItem(month);
		mDayWheel.setCurrentItem(day);
	}

	private void initListener() {
		// TODO Auto-generated method stub
		mYearWheel.addChangingListener(wheelListener_year);
		mMonthWheel.addChangingListener(wheelListener_month);
	}

	private void adapterScreen() {
		// TODO Auto-generated method stub
		// 根据屏幕密度来指定选择器字体的大小
		int textSize = pixelsToDip(mContext.getResources(), 13);
		 mDayWheel.TEXT_SIZE = textSize;
		 mMonthWheel.TEXT_SIZE = textSize;
		 mYearWheel.TEXT_SIZE = textSize;
	}

	public int pixelsToDip(Resources res, int pixels) {
		final float scale = res.getDisplayMetrics().density;
		return (int) (pixels * scale + 0.5f);
	}

	// 添加"年"监听
	private final WheelView.OnWheelChangedListener wheelListener_year = new WheelView.OnWheelChangedListener() {
		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			int year_num = newValue + mYearAdapter.getMin();
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(mMonthWheel.getCurrentItem() + 1))) {
				mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 31);
				mDayWheel.setAdapter(mDayAdapter);
			}
			else if (list_little.contains(String.valueOf(mMonthWheel.getCurrentItem() + 1))) {
				mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 30);
				mDayWheel.setAdapter(mDayAdapter);
			}
			else {
				if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
					mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 29);
					mDayWheel.setAdapter(mDayAdapter);
				}
				else {
					mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 28);
					mDayWheel.setAdapter(mDayAdapter);
				}
			}
		}
	};

	// 添加"月"监听
	private final WheelView.OnWheelChangedListener wheelListener_month = new WheelView.OnWheelChangedListener() {
		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			int month_num = newValue + 1;
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(month_num))) {
				mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 31);
				mDayWheel.setAdapter(mDayAdapter);
			}
			else if (list_little.contains(String.valueOf(month_num))) {
				mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 30);
				mDayWheel.setAdapter(mDayAdapter);
			}
			else {
				if (((mYearWheel.getCurrentItem() + mYearAdapter.getMin()) % 4 == 0 && (mYearWheel.getCurrentItem() + mYearAdapter
						.getMin()) % 100 != 0) || (mYearWheel.getCurrentItem() + mYearAdapter.getMin()) % 400 == 0) {
					mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 29);
					mDayWheel.setAdapter(mDayAdapter);
				}
				else {
					mDayAdapter = mDayWheel.new NumericWheelAdapter(1, 28);
					mDayWheel.setAdapter(mDayAdapter);
				}
			}
		}
	};

	public void setDate(Calendar calendar) {
		if (calendar == null)
			return;
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (list_big.contains(String.valueOf(month + 1))) {
			mDayWheel.setAdapter(mMonthWheel.new NumericWheelAdapter(1, 31));
		}
		else if (list_little.contains(String.valueOf(month + 1))) {
			mDayWheel.setAdapter(mMonthWheel.new NumericWheelAdapter(1, 30));
		}
		else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				mDayWheel.setAdapter(mMonthWheel.new NumericWheelAdapter(1, 29));
			else
				mDayWheel.setAdapter(mMonthWheel.new NumericWheelAdapter(1, 28));
		}
		mYearWheel.setCurrentItem(year - mYearAdapter.getMin());// 初始化时显示的数据
		mMonthWheel.setCurrentItem(month);
		mDayWheel.setCurrentItem(day - 1);
	}

	public int getYear() {
		return mYearWheel.getCurrentItem() + mYearAdapter.getMin();
	}

	public int getMonth() {
		return mMonthWheel.getCurrentItem() + 1;
	}

	public int getDay() {
		return mDayWheel.getCurrentItem() + 1;
	}

	public Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getYear(), getMonth(), getDay());
		return calendar;
	}

}
