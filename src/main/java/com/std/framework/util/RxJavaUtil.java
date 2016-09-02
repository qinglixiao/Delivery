package com.std.framework.util;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.internal.util.ActionSubscriber;

/**
 * Description :
 * Created by lx on 2016/8/23.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class RxJavaUtil {

    /**
     * 获取带有异常处理的Subscriber
     * @param onNext
     * @param <T>
     * @return
     */
    public static <T> Subscriber addOnNextActionErrorDeal(Action1<T> onNext) {
        if (onNext != null) {
            return new ActionSubscriber(onNext, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {

                }
            }, Actions.empty());
        } else {
            return null;
        }
    }
}
