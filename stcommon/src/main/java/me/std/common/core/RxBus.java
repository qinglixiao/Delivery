package me.std.common.core;

import io.reactivex.Observable;
import me.std.common.core.relay.PublishRelay;
import me.std.common.core.relay.Relay;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/8/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RxBus {
    private static volatile RxBus instance;
    private final Relay<Object> bus = PublishRelay.create().toSerialized();

    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object obj) {
        bus.accept(obj);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

}
