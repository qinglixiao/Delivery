package me.std.common.view.timepicker.listener;

import me.std.common.view.timepicker.DateScrollerDialog;

/**
 * 日期设置的监听器
 *
 * @author C.L. Wang
 */
public interface OnDateSetListener {
    void onDateSet(DateScrollerDialog timePickerView, long milliseconds);
}
