package com.std.framework;

import com.std.framework.assist.JunitUtil;
import com.std.framework.util.RxJavaUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.ActionSubscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/11.
 */
public class TestRxJava {

    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onStart() {
            JunitUtil.log("onStart");
        }

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
                JunitUtil.log("开始发送-你好！");
                subscriber.onNext("你好！");
                JunitUtil.log("开始发送-朋友！");
                subscriber.onNext("朋友");
                subscriber.onCompleted();
            }
        });
        rx.subscribe(subscriber);
    }

    @Test
    public void testJust() {
        Observable.just("我很好！", "...", "%&^*&").subscribe(subscriber);
    }

    @Test
    public void testFilter() {
        Observable.just("我很好！", "...", "%&^*&", "我你iojoljoi")
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s.startsWith("我")) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(subscriber);
    }

    @Test
    public void testFrom() {
        ArrayList<String> items = new ArrayList<>();
        items.add("中国");
        items.add("德国");
        items.add("美国");
        Observable.from(items).subscribe(subscriber);
    }

    @Test
    public void testRepeat() {
        Observable.just("我很好！", "...", "%&^*&", "我你iojoljoi").repeat(2).subscribe(subscriber);
    }

    @Test
    public void testTimer() {
        Observable.just("我很好！", "...", "%&^*&", "我你iojoljoi").timer(5, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                JunitUtil.log(aLong.toString());
            }
        });
        JunitUtil.sleep(8000);
    }

    @Test
    public void testMap() {
        Observable
                .just("德国", "意大利")
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        JunitUtil.log(Thread.currentThread().getName());
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        JunitUtil.log(Thread.currentThread().getName());
                        return s + "参加比赛";
                    }
                })
                .subscribe(subscriber);

    }

    @Test
    //无序执行
    public void testFlatMap() throws InterruptedException {
        ArrayList<String> items = new ArrayList<>();
        items.add("中国");
        items.add("德国");
        items.add("美国");
        Observable.from(items).repeat(5).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s + "第一", s + "第二", s + "第三");
            }
        }).subscribe(subscriber);

        Thread.sleep(100);
    }

    @Test
    //按顺序执行
    public void testConcatMap() throws InterruptedException {
        ArrayList<String> items = new ArrayList<>();
        items.add("中国");
        items.add("德国");
        items.add("美国");
        Observable.from(items).repeat(5).concatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s + "第一", s + "第二", s + "第三");
            }
        }).subscribe(subscriber);

        Thread.sleep(100);
    }

    @Test
    public void testZip() throws InterruptedException {
        List<String> hellos = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            hellos.add(String.format("hello %s:", i));
        }
        List<Integer> worlds = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            worlds.add(j);
        }

        Observable.zip(Observable.from(hellos).subscribeOn(Schedulers.io()), Observable.from(worlds).subscribeOn(Schedulers.io()), new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return s + integer.toString();
            }
        }).subscribe(subscriber);

        Thread.sleep(500);
    }

    @Test
    public void testNoSubcribe() {
        Observable.just("a", "b", "c").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                JunitUtil.log(s);
                return null;
            }
        }).subscribe();
    }

    @Test
    public void testOnSubcribe() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        JunitUtil.log("开始发送");
                        subscriber.onNext("发送");
                        subscriber.onCompleted();
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        JunitUtil.log("doOnSubscribe" + "---thread:" + (Thread.currentThread().getName()));
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        JunitUtil.log("doOnNext1:" + o + "---thread:" + (Thread.currentThread().getName()));
                        JunitUtil.sleep(100);
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JunitUtil.log("doOnNext2:" + s + "---thread:" + (Thread.currentThread().getName()));
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
        JunitUtil.sleep(200);
    }

    @Test
    public void testNullSubcribe() {
        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        subscriber.onNext("发送");
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        JunitUtil.log("接收");
                    }
                });
    }

    @Test
    public void testJustAsyn() {
        Observable
                .just(sample())
                .subscribeOn(Schedulers.io())
                .subscribe();

//        Observable
//                .create(new Observable.OnSubscribe<Object>() {
//                    @Override
//                    public void call(Subscriber<? super Object> subscriber) {
//                        subscriber.onNext(sample());
//                        subscriber.onCompleted();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe();
        JunitUtil.sleep(100);
    }

    private int sample() {
        for (int i = 0; i < 10; i++) {
            JunitUtil.sleep(10);
        }
        JunitUtil.log("thread:" + Thread.currentThread().getName());
        return 100;
    }

    @Test
    public void testFilterThread() {
        Observable
                .just(null)
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object o) {
                        JunitUtil.log("thread:" + Thread.currentThread().getName());
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Test
    public void testException() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        String aa = null;
                        aa.equals(0);
                        subscriber.onNext("ds");
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(RxJavaUtil.addOnNextActionErrorDeal(new Action1<String>() {
                                                                   @Override
                                                                   public void call(String s) {
                                                                       JunitUtil.log(s);
                                                                       s = null;
                                                                       s.equals(0);
                                                                   }
                                                               }

                ));
        JunitUtil.sleep(100);
    }

    @Test
    public void testInterval() {
        Observable.interval(1, 1, TimeUnit.SECONDS,Schedulers.computation())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        JunitUtil.log(aLong + "");

                    }
                });
        JunitUtil.sleep(20000);
    }
}
