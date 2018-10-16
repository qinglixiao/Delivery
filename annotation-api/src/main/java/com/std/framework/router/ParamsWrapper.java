package com.std.framework.router;

import android.text.TextUtils;

import com.std.framework.router.interfaces.IPromise;

import java.util.HashMap;
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
    private IPromise promise;

    private Map params = new HashMap();

    public IPromise getPromise() {
        return promise;
    }

    public void setPromise(IPromise promise) {
        this.promise = promise;
    }

    /**
     * @param query key=value&key=value
     */
    public void append(String query) {
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

    public Object get(String key) {
        return params.get(key);
    }
}
