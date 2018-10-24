package com.std.framework.router;

import android.content.Context;
import android.os.Looper;

import com.std.framework.router.exceptions.CYRouterException;
import com.std.framework.router.exceptions.ValueParseException;
import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.IPromise;
import com.std.framework.router.interfaces.Resolve;
import com.std.framework.router.utils.GenericUtil;
import com.std.framework.router.utils.PromiseTimer;
import com.std.framework.router.utils.RouterHelper;
import com.std.framework.router.utils.ValueParser;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class Promise<T> implements IPromise<T> {
    /**
     * 方法执行在子线程
     */
    public static final int FLAG_CALL_SUB_THREAD = 1;
    /**
     * 方法执行在主线程
     */
    public static final int FLAG_CALL_MAIN_THREAD = 1 << 1;
    /**
     * 回调发生在子线程
     */
    public static final int FLAG_RETURN_SUB_THREAD = 1 << 2;
    /**
     * 回调发生在主线程
     */
    public static final int FLAG_RETURN_MAIN_THREAD = 1 << 3;

    private Resolve resolve;
    private Capture capture;
    private Ask ask;
    private PromiseTimer timer;
    private int flagMark;

    public Promise(Ask ask) {
        this.ask = ask;
        ask.setPromise(new RPromise(this));
    }

    public void call(Resolve resolve, Capture capture) {
        this.resolve = resolve;
        this.capture = capture;

        if ((flagMark & FLAG_CALL_MAIN_THREAD) == 1 && !isMainThread()) {
            RouterHelper.HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    ask.request();
                }
            });
        } else if ((flagMark & FLAG_CALL_SUB_THREAD) == 1) {
            RouterHelper.EXECUTER.execute(new Runnable() {
                @Override
                public void run() {
                    ask.request();
                }
            });
        } else {
            ask.request();
        }
    }

    @Override
    public void resolve(final T data) {
        showToast();
        if (resolve == null) {
            return;
        }

        Object expected;
        try {
            String firstGeneric = GenericUtil.tryGetGeneric(resolve);
            expected = ValueParser.parse(data, firstGeneric);
        } catch (ValueParseException e) {
            capture(e);
            ask.release();
            return;
        }

        final Object expectedResult = expected;
        //return on main
        if ((flagMark & FLAG_RETURN_MAIN_THREAD) == 1 && !isMainThread()) {
            RouterHelper.HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        resolve.call(expectedResult);
                    } catch (Exception ex) {
                        capture(new CYRouterException(ex));
                    } finally {
                        ask.release();
                    }
                }
            });
        } else if ((flagMark & FLAG_RETURN_SUB_THREAD) == 1) {
            RouterHelper.EXECUTER.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        resolve.call(expectedResult);
                    } catch (Exception ex) {
                        capture(new CYRouterException(ex));
                    } finally {
                        ask.release();
                    }
                }
            });
        } else {
            try {
                resolve.call(expectedResult);
            } catch (Exception ex) {
                capture(new CYRouterException(ex));
            } finally {
                ask.release();
            }
        }
    }

    public void capture(final Exception ex) {
        if (capture == null) {
            return;
        }

        //return on main
        if ((flagMark & FLAG_RETURN_MAIN_THREAD) == 1 && !isMainThread()) {
            RouterHelper.HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    capture.exception(ex);
                    ask.release();
                }
            });
        } else if ((flagMark & FLAG_RETURN_SUB_THREAD) == 1) {
            RouterHelper.EXECUTER.execute(new Runnable() {
                @Override
                public void run() {
                    capture.exception(ex);
                    ask.release();
                }
            });
        } else {
            capture.exception(ex);
            ask.release();
        }
    }

    private void showToast() {
        if (timer == null) {
            return;
        }
        timer.show();
    }

    public void showTime(Context context) {
        timer = new PromiseTimer(context);
    }

    public void setRunFlag(int flag) {
        this.flagMark |= flag;
    }

    public boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
