package me.std.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/7.
 */
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(new Date());
    }

    public static String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        return format.format(new Date());
    }
}
