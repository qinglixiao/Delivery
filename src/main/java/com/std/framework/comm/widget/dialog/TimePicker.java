package com.std.framework.comm.widget.dialog;

import java.util.Calendar;

import com.std.framework.comm.widget.dialog.WheelView.ArrayWheelAdapter;
import com.std.framework.comm.widget.dialog.WheelView.NumericWheelAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class TimePicker extends LinearLayout {
	private WheelView mHourWheel;//小时滚轮
	private WheelView mMinuteWheel;//分钟滚轮
	private WheelView mFormatWheel;//时间格式滚轮(当采用12小时制时出现)
	private NumericWheelAdapter mHourAdapter;
	private NumericWheelAdapter mMinuteAdapter;
	private ArrayWheelAdapter<String> mFormatAdapter;
	private String[] hour12format = { "上午", "下午" };

	private Context mContext;
	private int mFormat = 0;
	private boolean is24Hour = true;
	private Calendar currentCalendar;

	public TimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		mContext = context;
		setOrientation(LinearLayout.HORIZONTAL);
		initView(context, attrs);
		adapterScreen();
		initAdapter();
		setDefaultTime();
	}

	private void initView(Context context, AttributeSet attrs) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		params.setMargins(2, 2, 2, 2);
		mHourWheel = new WheelView(context, attrs);
		mMinuteWheel = new WheelView(context, attrs);
		mFormatWheel = new WheelView(context, attrs);
		mHourWheel.setLabel("时");
		mMinuteWheel.setLabel("分");
		mFormatWheel.setCyclic(false);
		mFormatWheel.setCurrentItem(0);
		addView(mHourWheel, params);
		addView(mMinuteWheel, params);
		addView(mFormatWheel, params);
	}

	private void initAdapter() {
		mHourAdapter = mHourWheel.new NumericWheelAdapter(1, 12);
		mMinuteAdapter = mMinuteWheel.new NumericWheelAdapter(0, 59, "%02d");
		mFormatAdapter = mFormatWheel.new ArrayWheelAdapter<String>(hour12format);
		mHourWheel.setAdapter(mHourAdapter);
		mMinuteWheel.setAdapter(mMinuteAdapter);
		mFormatWheel.setAdapter(mFormatAdapter);
	}

	private void setDefaultTime() {
		is24Hour = DateFormat.is24HourFormat(mContext);
		resetHourAdapter();
		setDate(Calendar.getInstance());
	}

	public void setDate(Calendar calendar) {
		if (calendar == null) 
			calendar = currentCalendar;
		if (is24Hour) {
			mHourWheel.setCurrentItem(calendar.get(Calendar.HOUR_OF_DAY));
		}
		else {
			int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
			int hour = calendar.get(Calendar.HOUR);
			mHourWheel.setCurrentItem(hour == 0 ? 11 : hour - 1);
			if (hour24 >= 12)
				mFormatWheel.setCurrentItem(1);
			else
				mFormatWheel.setCurrentItem(0);
		}
		mMinuteWheel.setCurrentItem(calendar.get(Calendar.MINUTE));
		currentCalendar = calendar;
	}

	private void adapterScreen() {
		// TODO Auto-generated method stub
		// 根据屏幕密度来指定选择器字体的大小
		int textSize = pixelsToDip(mContext.getResources(), 13);
		 mHourWheel.TEXT_SIZE = textSize;
		 mMinuteWheel.TEXT_SIZE = textSize;
		 mFormatWheel.TEXT_SIZE = textSize;
	}

	public int pixelsToDip(Resources res, int pixels) {
		final float scale = res.getDisplayMetrics().density;
		return (int) (pixels * scale + 0.5f);
	}

	public Calendar getCalendar() {
		if (is24Hour)
			currentCalendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
					currentCalendar.get(Calendar.DAY_OF_MONTH), getHour(), getMinute());
		else {
			if (mFormatWheel.getCurrentItem() == 0) {
				currentCalendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
						currentCalendar.get(Calendar.DAY_OF_MONTH), getHour() + 1, getMinute());
			}
			else {
				currentCalendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
						currentCalendar.get(Calendar.DAY_OF_MONTH), getHour() + 13, getMinute());
			}
		}
		return currentCalendar;
	}

	private int getHour() {
		return mHourWheel.getCurrentItem();
	}

	private int getMinute() {
		return mMinuteWheel.getCurrentItem();
	}

	public void resetHourAdapter() {
		if (is24Hour) {
			mHourAdapter = mHourWheel.new NumericWheelAdapter(0, 23);
			mFormatWheel.setVisibility(View.GONE);
		}
		else {
			mHourAdapter = mHourWheel.new NumericWheelAdapter(1, 12);
			mFormatWheel.setVisibility(View.VISIBLE);
		}
		mHourWheel.setAdapter(mHourAdapter);
	}

	public void setIs24Hour(boolean is24) {
		is24Hour = is24;
		resetHourAdapter();
	}
}
