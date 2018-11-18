package com.std.framework;

import com.std.framework.assist.JunitUtil;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class TestRxJava2 {
    @Test
    public void testCreate() {
//        Flowable.generate(new Consumer<Emitter<String>>() {
//            @Override
//            public void accept(Emitter<String> emitter) throws Exception {
//                emitter.onNext("1");
//                emitter.onNext("2");
//                emitter.onNext("3");
//                emitter.onNext("4");
//                emitter.onNext("5");
//                emitter.onComplete();
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                JunitUtil.print(s);
//            }
//        });

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            /**
             * Called for each Subscriber that subscribes.
             *
             * @param emitter the safe emitter instance, never null
             * @throws Exception on error
             */
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        JunitUtil.print(integer);
                    }
                });
    }

    @Test
    public void testInterval() {
        Flowable.empty()
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {

                    /**
                     * Consume the given value.
                     *
                     * @param integer the value
                     * @throws Exception on error
                     */
                    @Override
                    public void accept(Object integer) throws Exception {
                        JunitUtil.print(integer.toString());
                    }
                });
    }

    @Test
    public void testFilter() {
        int[] src = {1, -1, 3, -4};

        Flowable.fromArray(src)

                .filter(new Predicate<int[]>() {
                    @Override
                    public boolean test(int[] ints) throws Exception {
//                        for (int i : ints) {
//                            if (i > 0) {
//                                return true;
//                            } else {
//                                return false;
//                            }
//                        }
                        return false;

                    }
                })
                .subscribe(new Consumer<int[]>() {
                    @Override
                    public void accept(int[] ints) throws Exception {
                        JunitUtil.print(Arrays.toString(ints));
                    }
                });
    }

}
