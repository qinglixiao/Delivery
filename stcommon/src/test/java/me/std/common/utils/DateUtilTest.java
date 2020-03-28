package me.std.common.utils;

import org.junit.Test;

import me.std.common.JunitLog;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/7.
 */
public class DateUtilTest {

    @Test
    public void testDateFormat() {
        JunitLog.print(DateUtil.getDate());
        JunitLog.print(DateUtil.getDateTime());
    }
}
