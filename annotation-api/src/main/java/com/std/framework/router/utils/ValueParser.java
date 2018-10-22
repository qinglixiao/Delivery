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
        if (TextUtils.isEmpty(expectedType) || arg == null) {
            return arg;
        } else if ("int".equals(expectedType) || "java.lang.Integer".equals(expectedType)) {
            return toInteger(arg, 0);
        } else if ("float".equals(expectedType) || "java.lang.Float".equals(expectedType)) {
            return toFloat(arg, 0f);
        } else if ("long".equals(expectedType) || "java.lang.Long".equals(expectedType)) {
            return toLong(arg, 0l);
        } else if ("double".equals(expectedType) || "java.lang.Double".equals(expectedType)) {
            return toDouble(arg, 0d);
        } else if ("boolean".equals(expectedType) || "java.lang.Boolean".equals(expectedType)) {
            return toBoolean(arg, false);
        } else if ("java.util.Map".equals(expectedType) || expectedType.contains("java.util.HashMap")) {

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

    private static Float toFloat(Object value, float defValue) {
        try {
            if (value instanceof String) {
                return Float.valueOf(value.toString());
            } else if (value instanceof Float) {
                return (Float) value;
            } else if (value instanceof Number) {
                return ((Number) value).floatValue();
            }
        } catch (Exception e) {
        }
        return defValue;
    }

    private static Long toLong(Object value, long defValue) {
        try {
            if (value instanceof String) {
                return Long.valueOf(value.toString());
            } else if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof Number) {
                return ((Number) value).longValue();
            }
        } catch (Exception e) {
        }
        return defValue;
    }

    private static Double toDouble(Object value, double defValue) {
        try {
            if (value instanceof String) {
                return Double.valueOf(value.toString());
            } else if (value instanceof Double) {
                return (Double) value;
            } else if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }
        } catch (Exception e) {
        }
        return defValue;
    }

    private static Boolean toBoolean(Object value, boolean defValue) {
        try {
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof String) {
                if ("true".equalsIgnoreCase(value.toString())) {
                    return true;
                } else if ("false".equalsIgnoreCase(value.toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return defValue;
    }


}
