package com.std.framework.router.utils;

import android.text.TextUtils;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/16.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ValueParser {

    public static Object parse(Object arg, String expectedType) {
        if (TextUtils.isEmpty(expectedType)) {
            return arg;
        } else if ("int".equals(expectedType) || "java.lang.Integer".equals(expectedType)) {
            return toInteger(arg, 0);
        }
        return null;
    }

    private static Integer toInteger(Object value, int defValue) {
        try {
            if (value instanceof String) {
                return Integer.valueOf(value.toString());
            } else if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            }
        } catch (Exception e) {
        }
        return defValue;
    }
}
