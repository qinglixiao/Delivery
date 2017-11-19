package com.std.framework.core;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Description : 基于Rxjava实现的事件线
 * Author: lx
 * Create on: 2016/7/13
 * Modify by：lx
 */
public class RxBus {
    private static volatile RxBus instance;

    private final Subject bus = new SerializedSubject(PublishSubject.create());

    public static RxBus getDefault(){
        if(instance == null){
            synchronized (RxBus.class){
                if(instance == null){
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object observable){
        bus.onNext(observable);
    }

    public <T> Observable<T> toObservable(Class<T> eventType){
        return bus.ofType(eventType);
    }

}
