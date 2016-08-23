package com.std.framework.util;

import rx.Observable;
import rx.functions.Func1;

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

    public static <T> Observable<T> attachErrorHandler(Observable<T> obs) {
        return obs.onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                System.out.println("Handling error by printint to console: " + throwable);
                return Observable.empty();
            }
        });
    }
}
