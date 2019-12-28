package me.std.common.view.timepicker.listener;


import me.std.common.view.timepicker.DateScrollerDialog;
import me.std.common.view.timepicker.TimeWheel;

/**
 * Created by Aaron Wang on 2017/12/5.
 *
 * 日期改变时的回调，年、月、日、小时的滚轮改变都可回调此接口，但都需要一个个设置
 */

public interface OnDateChangeListener {
    void onDateChange(DateScrollerDialog timePickerView, TimeWheel wheelView, long milliseconds);
}
