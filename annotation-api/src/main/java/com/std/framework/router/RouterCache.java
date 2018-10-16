package com.std.framework.router;

import android.text.TextUtils;
import android.util.LruCache;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RouterCache {
    private LruCache<String, Object> objs = new LruCache(30);

    public void addMirrorObj(String name, Object clazz) {
        synchronized (objs) {
            if (TextUtils.isEmpty(name) || clazz == null) {
                return;
            }
            if (objs.get(name) == null) {
                objs.put(name, clazz);
            }
        }

    }

    public Object getMirrorObj(String name) {
        synchronized (objs) {
            if (TextUtils.isEmpty(name)) {
                return null;
            }
            return objs.get(name);
        }
    }
}
