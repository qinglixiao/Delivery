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

    public CPromise showTime() {
        target.showTime();
        return this;
    }

    public CPromise runOnMainThread(){
        target.setRunFlag(Promise.FLAG_CALL_MAIN_THREAD);
        return this;
    }

    public CPromise runOnSubThread(){
        target.setRunFlag(Promise.FLAG_CALL_SUB_THREAD);
        return this;
    }

    public CPromise returnOnMainThread(){
        target.setRunFlag(Promise.FLAG_RETURN_MAIN_THREAD);
        return this;
    }

    public CPromise returnOnSubThread(){
        target.setRunFlag(Promise.FLAG_RETURN_SUB_THREAD);
        return this;
    }

    public <R> void done(Resolve<R> resolve) {
        done(resolve, null);
    }

    public <R> void done(Capture capture) {
        done(null, capture);
    }

    public <R> void done(Resolve<R> resolve, Capture capture) {
        target.call(resolve, capture);
    }

}
