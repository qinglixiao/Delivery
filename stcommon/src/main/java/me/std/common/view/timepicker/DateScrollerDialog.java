package me.std.common.view.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.std.common.R;
import me.std.common.view.timepicker.config.ScrollerConfig;
import me.std.common.view.timepicker.data.Type;
import me.std.common.view.timepicker.data.WheelCalendar;
import me.std.common.view.timepicker.listener.OnDateChangeListener;
import me.std.common.view.timepicker.listener.OnDateSetListener;
import me.std.common.view.timepicker.wheel.OnWheelChangedListener;
import me.std.common.view.timepicker.wheel.WheelView;

/**
 * 时间选择框, 设置若干参数
 *
 * @author C.L.Wang
 */
public class DateScrollerDialog extends DialogFragment implements View.OnClickListener {
    private ScrollerConfig mScrollerConfig;
    private TimeWheel mTimeWheel;
    private long mCurrentMilliseconds;
    private TextView mTitle;
    private OnCloseListener onCloseListener;


    private static final long HUNDRED_YEARS = 119L * 365 * 1000 * 60 * 60 * 24L; // 119年

    // 实例化参数, 传入数据
    private static DateScrollerDialog newInstance(ScrollerConfig scrollerConfig) {
        DateScrollerDialog dateScrollerDialog = new DateScrollerDialog();
        dateScrollerDialog.initialize(scrollerConfig);
        return dateScrollerDialog;
    }

    public void setOnCloseListener(OnCloseListener closeListener) {
        this.onCloseListener = closeListener;
    }

    /**
     * 使用的便捷方式
     *
     * @param fm  FragmentManager
     * @param cur 当前时间
     * @param cb  回调
     */
    public static void showDateDialog(FragmentManager fm, long cur, OnDateSetListener cb) {
        showDateDialog(fm, cur, getFirstTime(), System.currentTimeMillis(), cb);
    }

    /**
     * 使用的便捷方式
     *
     * @param fm    FragmentManager
     * @param cur   当前时间
     * @param cb    回调
     * @param year  当年份改变时回调
     * @param month 当月份改变时的回调
     * @param day   当天改变时的回调
     * @param hour  当小时改变时的回调
     */
    public static void showDateDialog(FragmentManager fm, long cur,
                                      OnDateSetListener cb,
                                      OnDateChangeListener year,
                                      OnDateChangeListener month,
                                      OnDateChangeListener day,
                                      OnDateChangeListener hour,
                                      String title) {
        showDateDialog(fm, cur, getFirstTime(), System.currentTimeMillis(), cb, year, month, day, hour, title);
    }

    /**
     * 使用的便捷方式
     *
     * @param fm  FragmentManager
     * @param cur 当前时间
     * @param min 最小时间
     * @param max 最大时间
     * @param cb  回调
     */
    public static void showDateDialog(FragmentManager fm, long cur, long min, long max, OnDateSetListener cb) {
        showDateDialog(fm, cur, min, max, cb, null, null, null, null, null);
    }

    /**
     * 使用的便捷方式
     *
     * @param fm  FragmentManager
     * @param cur 当前时间
     * @param min 最小时间
     * @param max 最大时间
     * @param cb  回调
     */
    public static void showDateDialog(FragmentManager fm, long cur, long min, long max,
                                      OnDateSetListener cb,
                                      OnDateChangeListener year,
                                      OnDateChangeListener month,
                                      OnDateChangeListener day,
                                      OnDateChangeListener hour,
                                      String title) {
        // 年月日
        DateScrollerDialog dialog = new Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setMinMilliseconds(min)
                .setMaxMilliseconds(max)
                .setCurMilliseconds(cur)
                .setCallback(cb)
                .setOnYearChangeListener(year)
                .setOnMonthChangeListener(month)
                .setOnDayChangeListener(day)
                .setOnHourChangeListener(hour)
                .setTitleStringId(title)
                .build();

        // 防止重复添加
        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(fm, "year_month_day");
            }
        }
    }

    /**
     * 获取时间字符串
     *
     * @param time 毫秒
     * @return 字符串
     */
    public static String getTimeStr(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return format.format(time);
    }

    /**
     * 获取初始时间
     *
     * @return 毫秒
     */
    public static long getFirstTime() {
        String first = "1900-01-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(first);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            return date.getTime();
        } else {
            return System.currentTimeMillis() - HUNDRED_YEARS;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getActivity().getWindow();
        // 隐藏软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Dialog的高度
//        int height = getResources().getDimensionPixelSize(R.dimen.picker_height);

        // Dialog的位置置底
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
        }
    }

    /**
     * 初始化参数, 来源{@link #newInstance(ScrollerConfig)}
     *
     * @param scrollerConfig 滚动参数
     */
    private void initialize(ScrollerConfig scrollerConfig) {
        mScrollerConfig = scrollerConfig;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog_NoTitle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true); // 后退键取消
        dialog.setCanceledOnTouchOutside(true); // 点击外面被取消
        dialog.setContentView(initView()); // 设置View
        return dialog;
    }

    /**
     * 初始化视图
     *
     * @return 当前视图
     */
    private View initView() {
        Context context = getActivity().getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.timepicker_layout, nullParent);

        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(this); // 设置取消按钮
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this); // 设置确认按钮
        mTitle = (TextView) view.findViewById(R.id.tv_title);

        // 设置顶部栏
        View toolbar = view.findViewById(R.id.toolbar); // 头部视图
        toolbar.setBackgroundResource(mScrollerConfig.mToolbarBkgColor);
        mTitle.setText(mScrollerConfig.mTitleString); // 设置文字
        cancel.setText(mScrollerConfig.mCancelString);
        sure.setText(mScrollerConfig.mSureString);

        mTimeWheel = new TimeWheel(view, mScrollerConfig); // 设置滚动参数

        // 设置日期滚轮监听
        if (mScrollerConfig.mOnYearChangeListener != null) {
            mTimeWheel.addOnYearChangeListener(generateOnWheelChangeListener(mScrollerConfig.mOnYearChangeListener));
        }
        if (mScrollerConfig.mOnMonthChangeListener != null) {
            mTimeWheel.addOnMonthChangeListener(generateOnWheelChangeListener(mScrollerConfig.mOnMonthChangeListener));
        }
        if (mScrollerConfig.mOnDayChangeListener != null) {
            mTimeWheel.addOnDayChangeListener(generateOnWheelChangeListener(mScrollerConfig.mOnDayChangeListener));
        }
        if (mScrollerConfig.mOnHourChangeListener != null) {
            mTimeWheel.addOnHourChangeListener(generateOnWheelChangeListener(mScrollerConfig.mOnHourChangeListener));
        }
        if (mScrollerConfig.mOnMiniteChangeListener != null) {
            mTimeWheel.addOnHourChangeListener(generateOnWheelChangeListener(mScrollerConfig.mOnMiniteChangeListener));
        }

        return view;
    }

    /**
     * 将自定义监听转化为{@link OnWheelChangedListener}
     *
     * @param onDateChangeListener 符合需求的自定义的监听
     * @return OnWheelChangedListener
     */
    private OnWheelChangedListener generateOnWheelChangeListener(final OnDateChangeListener onDateChangeListener) {
        return new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                Calendar calendar = Calendar.getInstance();
                calendar.clear();

                calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
                calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth() - 1);
                calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
                calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
                calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());

                long currentMilliseconds = calendar.getTimeInMillis();
                onDateChangeListener.onDateChange(DateScrollerDialog.this, mTimeWheel, currentMilliseconds);
            }
        };
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss(); // 取消
            if (onCloseListener != null) {
                onCloseListener.onClose(0, mTimeWheel);
            }
        } else if (i == R.id.tv_sure) {
            onSureClicked();
            if (onCloseListener != null) {
                onCloseListener.onClose(1, mTimeWheel);
            }
        }
    }

    /**
     * 返回当前的选择秒数
     *
     * @return 当前秒数
     */
    public long getCurrentMilliseconds() {
        if (mCurrentMilliseconds == 0)
            return System.currentTimeMillis();

        return mCurrentMilliseconds;
    }

    /**
     * 确认按钮, 回调秒数
     */
    private void onSureClicked() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
        calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
        calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
        calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());

        mCurrentMilliseconds = calendar.getTimeInMillis();
        if (mScrollerConfig.mCallback != null) {
            mScrollerConfig.mCallback.onDateSet(this, mCurrentMilliseconds);
        }
        dismiss();
    }

    public TextView getTitleView() {
        return mTitle;
    }

    public static class Builder {
        ScrollerConfig mScrollerConfig;

        public Builder() {
            mScrollerConfig = new ScrollerConfig();
        }

        public Builder setType(Type type) {
            mScrollerConfig.mType = type;
            return this;
        }

        public Builder setThemeColor(@ColorRes int color) {
            mScrollerConfig.mToolbarBkgColor = color;
            return this;
        }

        public Builder setCancelStringId(String left) {
            mScrollerConfig.mCancelString = left;
            return this;
        }

        public Builder setSureStringId(String right) {
            mScrollerConfig.mSureString = right;
            return this;
        }

        public Builder setTitleStringId(String title) {
            mScrollerConfig.mTitleString = title;
            return this;
        }

        public Builder setToolBarTextColor(int color) {
            mScrollerConfig.mToolBarTVColor = color;
            return this;
        }

        public Builder setWheelItemTextNormalColor(int color) {
            mScrollerConfig.mWheelTVNormalColor = color;
            return this;
        }

        public Builder setWheelItemTextSelectorColor(int color) {
            mScrollerConfig.mWheelTVSelectorColor = color;
            return this;
        }

        public Builder setWheelItemTextSize(int size) {
            mScrollerConfig.mWheelTVSize = size;
            return this;
        }

        public Builder setCyclic(boolean cyclic) {
            mScrollerConfig.cyclic = cyclic;
            return this;
        }

        public Builder setTimeInterval(int minute){
            mScrollerConfig.mTimeInterval = minute;
            return this;
        }


        public Builder setMinMilliseconds(long milliseconds) {
            mScrollerConfig.mMinCalendar = new WheelCalendar(milliseconds);
            return this;
        }

        public Builder setMaxMilliseconds(long milliseconds) {
            mScrollerConfig.mMaxCalendar = new WheelCalendar(milliseconds);
            return this;
        }

        public Builder setCurMilliseconds(long milliseconds) {
            mScrollerConfig.mCurCalendar = new WheelCalendar(milliseconds);
            return this;
        }

        public Builder setYearText(String year) {
            mScrollerConfig.mYear = year;
            return this;
        }

        public Builder setMonthText(String month) {
            mScrollerConfig.mMonth = month;
            return this;
        }

        public Builder setDayText(String day) {
            mScrollerConfig.mDay = day;
            return this;
        }

        public Builder setHourText(String hour) {
            mScrollerConfig.mHour = hour;
            return this;
        }

        public Builder setMinuteText(String minute) {
            mScrollerConfig.mMinute = minute;
            return this;
        }

        public Builder setCallback(OnDateSetListener listener) {
            mScrollerConfig.mCallback = listener;
            return this;
        }

        public Builder setOnYearChangeListener(OnDateChangeListener listener) {
            mScrollerConfig.mOnYearChangeListener = listener;
            return this;
        }

        public Builder setOnMonthChangeListener(OnDateChangeListener listener) {
            mScrollerConfig.mOnMonthChangeListener = listener;
            return this;
        }

        public Builder setOnDayChangeListener(OnDateChangeListener listener) {
            mScrollerConfig.mOnDayChangeListener = listener;
            return this;
        }

        public Builder setOnHourChangeListener(OnDateChangeListener listener) {
            mScrollerConfig.mOnHourChangeListener = listener;
            return this;
        }

        public Builder setOnMinuteChangeListener(OnDateChangeListener listener) {
            mScrollerConfig.mOnMiniteChangeListener = listener;
            return this;
        }

        public DateScrollerDialog build() {
            return newInstance(mScrollerConfig);
        }
    }

    public interface OnCloseListener {
        /**
         * 弹窗关闭回调
         *
         * @param which 0：取消，1：确认
         */
        void onClose(int which, TimeWheel timeWheel);
    }
}
