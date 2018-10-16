package com.std.framework.router.utils;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/16.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class PromiseTimer {
    private long start;

    public PromiseTimer() {
        start = System.currentTimeMillis();
    }

    public String time() {
        return (System.currentTimeMillis() - start) + " ms";
    }
}
