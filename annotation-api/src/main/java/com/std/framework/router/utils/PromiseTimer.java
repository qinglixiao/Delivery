package com.std.framework.router.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/16.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class PromiseTimer {
    private Context context;
    private long start;

    public PromiseTimer(Context context) {
        this.context = context;
        start = System.currentTimeMillis();
    }

    public void show() {
        Toast.makeText(context, (System.currentTimeMillis() - start) + " ms", Toast.LENGTH_SHORT).show();
    }
}
