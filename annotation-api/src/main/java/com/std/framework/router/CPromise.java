package com.std.framework.router;

import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.Resolve;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CPromise<T> {
    private Promise target;

    public CPromise(Promise promise) {
        target = promise;
    }

    public void call(Resolve<T> resolve) {
        target.call(resolve, null);
    }

    public void call(Resolve<T> resolve, Capture capture) {
        target.call(resolve, capture);
    }

}
