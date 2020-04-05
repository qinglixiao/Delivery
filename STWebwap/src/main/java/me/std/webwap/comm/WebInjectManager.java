package me.std.webwap.comm;

import android.content.Context;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于在app启动时，注入webview bridge回调方法。
 *
 * Created by zhangyunhua on 16/3/17.
 */
public class WebInjectManager {

    private static WebInjectManager sInstance = null;
    private Context mContext = null;
    private Map<String, JsInvokeMethodCallback> mInvokeMap = new HashMap<String, JsInvokeMethodCallback>();
    private Map<String, JsCallMethodCallback> mCallMap = new HashMap<String, JsCallMethodCallback>();

    public static WebInjectManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WebInjectManager(context);
        }
        return sInstance;
    }

    private WebInjectManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /**
     * 供AndroidJs的invoke方法回调
     * @param method 方法名
     * @param jsonStr 参数
     * @return 调用结果
     */
    public boolean invoke(Context context, String method, String jsonStr) {
        JsInvokeMethodCallback callback = mInvokeMap.get(method);
        if (callback != null) {
            return callback.invoke(context, jsonStr);
        }
        return false;
    }

    /**
     * 供AndroidJs的call方法回调
     * @param method 方法名
     * @param params 参数
     * @return 回调结果
     */
    public JSONObject call(String method, JSONObject params) {
        JsCallMethodCallback callback = mCallMap.get(method);
        if (callback != null) {
            return callback.call(params);
        }
        return null;
    }

    /**
     * 注入invoke方法回调的callback
     * @param method 方法名
     * @param callback callback
     */
    public void injectInvokeMethodCallback(String method, JsInvokeMethodCallback callback) {
        mInvokeMap.put(method, callback);
    }

    /**
     * 注入call方法回调的callback
     * @param method 方法名
     * @param callback callback
     */
    public void injectCallMethodCallback(String method, JsCallMethodCallback callback) {
        mCallMap.put(method, callback);
    }

    public interface JsInvokeMethodCallback {
        boolean invoke(Context context, String jsonStr);
    }

    public interface JsCallMethodCallback {
        JSONObject call(JSONObject params);
    }

}
