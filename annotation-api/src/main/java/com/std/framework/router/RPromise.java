package com.std.framework.router;

import com.std.framework.router.interfaces.IPromise;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RPromise<T> implements IPromise<T> {
    private IPromise target;

    public RPromise(IPromise promise) {
        target = promise;
    }

    @Override
    public void resolve(T data) {
        target.resolve(data);
    }

    @Override
    public void capture(Exception ex) {
        target.capture(ex);
    }
}
