package com.std.framework;

import com.std.framework.assist.JunitUtil;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/11.
 */
public class TestRxJava {

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            JunitUtil.log("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            JunitUtil.log(e.getMessage());
        }

        @Override
        public void onNext(String o) {
            JunitUtil.log(o);
        }
    };

    @Test
    public void testCall() {
        Observable<String> rx = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("你好！");
                subscriber.onNext("朋友");
                subscriber.onCompleted();
            }
        });
        rx.subscribe(subscriber);
    }

    @Test
    public void testJust(){
        Observable.just("我很好！","...","%&^*&").subscribe(subscriber);
    }
}
