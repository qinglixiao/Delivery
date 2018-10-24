package com.std.framework.router.utils;

import android.text.TextUtils;

import com.std.framework.router.ParamsWrapper;
import com.std.framework.router.exceptions.ValueParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/16.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ValueParser {

    public static Object parse(Object arg, String expectedType) throws ValueParseException {
        if (TextUtils.isEmpty(expectedType) || arg == null) {
            return arg;
        } else if ("int".equals(expectedType) || "java.lang.Integer".equals(expectedType)) {
            arg = toInteger(arg, 0);
        } else if ("float".equals(expectedType) || "java.lang.Float".equals(expectedType)) {
            arg = toFloat(arg, 0f);
        } else if ("long".equals(expectedType) || "java.lang.Long".equals(expectedType)) {
            arg = toLong(arg, 0l);
        } else if ("double".equals(expectedType) || "java.lang.Double".equals(expectedType)) {
            arg = toDouble(arg, 0d);
        } else if ("boolean".equals(expectedType) || "java.lang.Boolean".equals(expectedType)) {
            arg = toBoolean(arg, false);
        } else if ("java.util.Map".equals(expectedType) || expectedType.contains("java.util.HashMap")) {
            arg = toMap(arg, expectedType);
        } else if (expectedType.contains("[]")) {
            arg = toArray(arg, expectedType);
        } else if (expectedType.contains("java.util.List") || expectedType.contains("java.util.ArrayList")) {
            arg = toList(arg, expectedType);
        } else if (!"java.lang.Object".equals(expectedType)) {
            arg = toTargetObj(arg, expectedType);
        }
        return arg;
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

    private static Object toMap(Object value, String expectedType) throws ValueParseException {
        try {
            if (value instanceof String || value instanceof JSONObject) {
                JSONObject jObj;
                if (value instanceof JSONObject) {
                    jObj = (JSONObject) value;
                } else {
                    //checked string format
                    if (!isJson(value.toString())) {
                        throw new ValueParseException("Expected " + expectedType + ",But The input string isn't json.");
                    }
                    jObj = new JSONObject((String) value);
                }

                HashMap<String, String> map = new HashMap<>(jObj.length());
                Iterator<String> it = jObj.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    map.put(key, jObj.get(key).toString());
                }
                value = map;
            }
        } catch (Exception e) {
            throw new ValueParseException("parse to " + expectedType + " type fail.", e);
        }
        return value;
    }

    private static Object toArray(Object from, String expectedType) throws ValueParseException {
        try {
            if (from instanceof String || from instanceof JSONArray) {
                JSONArray jArray;
                if (from instanceof JSONArray) {
                    jArray = (JSONArray) from;
                } else {
                    //checked string format
                    if (!isJson(from.toString())) {
                        throw new ValueParseException("Expected " + expectedType + ",But The input string isn't json.");
                    }
                    jArray = new JSONArray((String) from);
                }

                String type = getExpectedArrayType(expectedType);
                Object target = newArray(type, jArray.length());
                int length = jArray.length();
                for (int i = 0; i < length; i++) {
                    setArray(target, type, i, jArray.get(i));
                }
                from = target;
            }

            //Instance array to expected array
            else if (from != null &&
                    from.getClass().isArray() &&
                    !from.getClass().getCanonicalName().equals(expectedType)) {
                Object[] origin = (Object[]) from;
                String type = getExpectedArrayType(expectedType);
                Object firstEle = origin.length == 0 ? null : origin[0];
                if (firstEle != null && !firstEle.getClass().getCanonicalName().equals(type)) {
                    Object target = newArray(type, origin.length);
                    for (int i = 0; i < origin.length; i++) {
                        setArray(target, type, i, origin[i]);
                    }
                    from = target;
                }
            }
        } catch (Exception e) {
            throw new ValueParseException(from.getClass().getCanonicalName() + " parse to " + expectedType + " type fail.", e);
        }
        return from;
    }

    private static boolean isJson(String str) {
        return (str.contains("{") && str.lastIndexOf("}") != -1) ||
                (str.contains("[") && str.lastIndexOf("]") != -1);
    }

    private static String getExpectedArrayType(String expectedType) {
        if (expectedType.contains("[]")) {
            expectedType = expectedType.replace("[]", "");
        }
        return expectedType;
    }

    private static Object newArray(String arrayType, int length) throws ClassNotFoundException {
        Object arr;
        if ("int".equals(arrayType)) {
            arr = new int[length];
        } else if ("boolean".equals(arrayType)) {
            arr = new boolean[length];
        } else if ("long".equals(arrayType)) {
            arr = new long[length];
        } else if ("double".equals(arrayType)) {
            arr = new double[length];
        } else if ("float".equals(arrayType)) {
            arr = new float[length];
        } else if ("java.lang.String".equals(arrayType)) {
            arr = new String[length];
        } else {
            arr = Array.newInstance(Class.forName(arrayType), length);
        }
        return arr;
    }

    private static void setArray(Object array, String arrayType, int index, Object value) throws ValueParseException {
        if ("int".equals(arrayType)) {
            Array.setInt(array, index, (Integer) parse(value, arrayType));
        } else if ("boolean".equals(arrayType)) {
            Array.setBoolean(array, index, (Boolean) parse(value, arrayType));
        } else if ("long".equals(arrayType)) {
            Array.setLong(array, index, (Long) parse(value, arrayType));
        } else if ("double".equals(arrayType)) {
            Array.setDouble(array, index, (Double) parse(value, arrayType));
        } else if ("float".equals(arrayType)) {
            Array.setFloat(array, index, (Float) parse(value, arrayType));
        } else {
            Array.set(array, index, parse(value, arrayType));
        }
    }

    private static Object toList(Object from, String expectedType) throws ValueParseException {
        try {
            if (from instanceof String || from instanceof JSONArray) {
                JSONArray jArray = from instanceof String ? new JSONArray((String) from) : (JSONArray) from;
                String generic = getListGeneric(expectedType);
                ArrayList<Object> list = new ArrayList<>(jArray.length());
                int length = jArray.length();
                for (int i = 0; i < length; i++) {
                    Object o = jArray.get(i);
                    list.add(parse(o, generic));
                }
                from = list;
            } else if (from instanceof List) {
                List origin = (List) from;
                String listGeneric = getListGeneric(expectedType);
                if (origin.size() != 0 && !origin.get(0).getClass().getCanonicalName().equalsIgnoreCase(listGeneric)) {
                    List<Object> target = new ArrayList<>(origin.size());
                    for (Object o : origin) {
                        target.add(parse(o, listGeneric));
                    }
                    from = target;
                }
            } else if (from instanceof Map) {
                Object params = ((Map) from).get(ParamsWrapper._CPARAM_);
                from = parse(params, expectedType);
            }
        } catch (JSONException e) {
            throw new ValueParseException("parse to " + expectedType + " type fail.", e);
        }
        return from;
    }

    private static String getListGeneric(String type) {
        return type.contains("<") ? type.substring(type.indexOf("<") + 1, type.indexOf(">")) : "";
    }

    private static Object toTargetObj(Object from, String expectType) throws ValueParseException {
        if (from instanceof String || from instanceof JSONObject) {
            from = parseJsonToTarget(from, expectType);
        } else if (from instanceof Map) {
            from = parseMapToTarget(from, expectType);
        } else if (!expectType.equalsIgnoreCase(from.getClass().getCanonicalName())) {
            from = parseObjToTarget(from, expectType);
        }
        return from;
    }

    //from json => to obj
    @SuppressWarnings("all")
    private static Object parseJsonToTarget(Object from, String expectType) throws ValueParseException {
        try {
            if (!isJson(from.toString())) return from;
            JSONObject jObj = from instanceof String ? new JSONObject((String) from) : (JSONObject) from;
            Class<?> targetClass = Class.forName(getNoGenericTypeName(expectType));
            Object target = targetClass.newInstance();
            do {
                Field[] fields = targetClass.getDeclaredFields();
                for (Field f : fields) {
                    String name = f.getName();
                    String type = getFieldTypeWithGeneric(f);
                    f.setAccessible(true);
                    f.set(target, parse(jObj.get(name), type));
                }
                targetClass = targetClass.getSuperclass();
            } while (!(targetClass == Object.class));
            from = target;
        } catch (JSONException e) {
            throw new ValueParseException(e);
        } catch (Exception e) {
            throw new ValueParseException("parse to " + expectType + " type fail.", e);
        }
        return from;
    }

    //from params => to obj
    private static Object parseMapToTarget(Object from, String expectType) throws ValueParseException {
        try {
            Class<?> targetClass = Class.forName(getNoGenericTypeName(expectType));
            Object target = targetClass.newInstance();
            Map params = (Map) from;
            do {
                Field[] toFields = targetClass.getDeclaredFields();
                for (Field toF : toFields) {
                    String toKey = toF.getName();
                    Object fromValue = params.get(toKey);
                    toF.setAccessible(true);
                    toF.set(target, parse(fromValue, getFieldTypeWithGeneric(toF)));
                }
                targetClass = targetClass.getSuperclass();
            } while (!(targetClass == Object.class));
            from = target;
        } catch (Exception e) {
            throw new ValueParseException("parse to " + expectType + " type fail.", e);
        }
        return from;
    }

    //from obj => to obj
    private static Object parseObjToTarget(Object from, String expectType) throws ValueParseException {
        try {
            if (isInherit(from.getClass(), expectType)) {
                return from;
            }
            Class<?> targetClass = Class.forName(getNoGenericTypeName(expectType));
            Object target = targetClass.newInstance();
            Map<String, Object> kvs = extractKeyValue(from);
            do {
                Field[] toFields = targetClass.getDeclaredFields();
                for (Field toF : toFields) {
                    toF.setAccessible(true);
                    Object fromValue = kvs.get(toF.getName());
                    toF.set(target, parse(fromValue, getFieldTypeWithGeneric(toF)));
                }
                targetClass = targetClass.getSuperclass();
            } while (!(targetClass == Object.class));
            from = target;
        } catch (Exception e) {
            throw new ValueParseException("parse to " + expectType + " type fail.", e);
        }
        return from;
    }

    private static boolean isInherit(Class<?> from, String parentType) {
        while (from != null) {
            if (from.getCanonicalName().equals(parentType)) {
                return true;
            } else {
                from = from.getSuperclass();
            }
        }
        return false;
    }

    private static Map<String, Object> extractKeyValue(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        HashMap<String, Object> kvs = new HashMap<>();
        do {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(obj);
                kvs.put(f.getName(), value);
            }
            clazz = clazz.getSuperclass();
        } while (!(clazz == Object.class));
        return kvs;
    }

    private static String getNoGenericTypeName(String className) {
        int index = className.indexOf("<");
        if (index != -1) {
            className = className.substring(0, index);
        }
        return className;
    }

    public static String getFieldTypeWithGeneric(Field f) {
        Class fieldType = f.getType();
        String type = fieldType.getCanonicalName();
        if (fieldType.isAssignableFrom(List.class)) {
            try {
                ParameterizedType pt = (ParameterizedType) f.getGenericType();
                return pt.toString();
            } catch (ClassCastException ignored) {
            }
        }
        return type;
    }


}
