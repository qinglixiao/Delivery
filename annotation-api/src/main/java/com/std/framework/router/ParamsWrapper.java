package com.std.framework.router;

import android.text.TextUtils;

import com.std.framework.router.exceptions.CYRouterException;
import com.std.framework.router.interfaces.IPromise;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/11.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ParamsWrapper {
    public static final String _CPARAM_ = "_CPARAM_";
    private IPromise promise;

    private Map params = new HashMap();

    public IPromise getPromise() {
        return promise;
    }

    public void setPromise(IPromise promise) {
        this.promise = promise;
    }

    /**
     * url query
     *
     * @param query key=value&key=value
     */
    public void withQueryString(String query) {
        if (TextUtils.isEmpty(query)) {
            return;
        }
        String[] pairs = query.split("&");
        for (String item : pairs) {
            String[] kv = item.split("=");
            if (kv != null && kv.length > 1) {
                params.put(kv[0], kv[1]);
            }
        }

    }

    public void append(Object params) throws Exception {
        if (params instanceof Map) {
            this.params.putAll((Map) params);
        } else if (params instanceof List) {
            this.params.put(_CPARAM_, params);
        } else if (params instanceof Object[]) {
            Object[] p = (Object[]) params;
            int length = p.length;
            if (length % 2 != 0) {
                throw new CYRouterException("object[] must be dual!!");
            }
            for (int i = 0; i < length; i += 2) {
                if (p[i] != null) {
                    this.params.put(p[i].toString(), p[i + 1]);
                }
            }
        } else if (params instanceof String) {
            try {
                JSONObject jsonObject = new JSONObject(params.toString());
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object value = jsonObject.get(key);
                    this.params.put(key, value);
                }
            } catch (JSONException e) {
                throw new CYRouterException(e);
            }
        }

    }

    public Object get(String key) {
        return params.get(key);
    }

    public void clear() {
        params.clear();
    }
}
