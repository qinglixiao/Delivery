package com.std.framework.router;

import android.app.Application;
import android.widget.Toast;

import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.IPromise;
import com.std.framework.router.interfaces.Resolve;
import com.std.framework.router.utils.PromiseTimer;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class Promise<T> implements IPromise<T> {
    private Application context;
    private Resolve resolve;
    private Capture capture;
    private Ask ask;
    private PromiseTimer timer;

    public Promise(Ask ask) {
        this.ask = ask;
        ask.setPromise(new RPromise(this));
    }

    public void call(Resolve resolve, Capture capture) {
        this.resolve = resolve;
        this.capture = capture;

        ask.request();
    }

    @Override
    public void resolve(T data) {
        showToast();
        resolve.call(data);
    }

    public void capture(Exception ex) {
        capture.exception(ex);
    }

    public void setContext(Application application) {
        this.context = application;
    }

    private void showToast() {
        if (timer == null) {
            return;
        }
        Toast.makeText(context, timer.time(), Toast.LENGTH_SHORT).show();
    }

    public Promise showTime() {
        timer = new PromiseTimer();
        return this;
    }
}
