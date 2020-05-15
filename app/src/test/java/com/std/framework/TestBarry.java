package com.std.framework;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/4/2.
 */
public class TestBarry {
    @Test
    public void test() {
        final CyclicBarrier barrier = new CyclicBarrier(2, myThread);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "thread2").start();
    }

    Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("myThread");
        }
    }, "thread3");
}
