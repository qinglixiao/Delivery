package me.std.common.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.std.common.utils.Logger;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class DataConvert {
    private static Gson gson = new Gson();

    public static <T> T fromJson(String obj, Class<T> clazz) {
        try {
            return gson.fromJson(obj, clazz);
        } catch (JsonSyntaxException ex) {
            Logger.e(String.format("【%s】\n%s", ex.getMessage(), obj));
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.e(String.format("【%s】\n%s", ex.getMessage(), obj));
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String obj, Type type) {
        try {
            return gson.fromJson(obj, type);
        } catch (JsonSyntaxException ex) {
            Logger.e(String.format("【%s】\n%s", ex.getMessage(), obj));
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.e(String.format("【%s】\n%s", ex.getMessage(), obj));
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> String toJson(T obj) {
        try {
            return gson.toJson(obj);
        } catch (JsonSyntaxException ex) {
            Logger.e(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.e(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> toMap(JSONObject obj) {
        if (obj != null) {
            Map<String, Object> map = new HashMap();
            Iterator<String> i = obj.keys();
            while (i.hasNext()) {
                String k = i.next();
                try {
                    Object v = obj.get(k);
                    if (v instanceof JSONArray) {
                        map.put(k, toList((JSONArray) v));
                    } else if (v instanceof JSONObject) {
                        map.put(k, toMap((JSONObject) v));
                    } else {
                        map.put(k, v);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return map;
        }
        return null;
    }

    public static Map<String, String> toMapS(JSONObject obj) {
        Map<String, Object> m = toMap(obj);
        if (m == null) return null;

        Map<String, String> r = new HashMap<>();
        for (String k : m.keySet()) {

            r.put(k, m.get(k).toString());
        }
        return r;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                list.add(toList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                list.add(toMap((JSONObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }
}
