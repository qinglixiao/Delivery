package me.std.common.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:线程相关操作处理
 * Author: lixiao
 * Create on: 2018/8/28.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ThreadPool {
    private static Handler mMainHandler = new Handler(Looper.getMainLooper());
    private static Handler mAsynHandler;
    private static final ExecutorService threadExecutor;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new ArrayBlockingQueue<>(CPU_COUNT * 10 + 2);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool newThread: thread #" + mCount.getAndIncrement());
        }
    };

    static {
        HandlerThread handlerThread = new HandlerThread("chunyu_asyn_thread");
        handlerThread.start();
        mAsynHandler = new Handler(handlerThread.getLooper());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory, new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                mAsynHandler.post(r);
            }
        });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadExecutor = threadPoolExecutor;
    }

    /**
     * 在主线程执行事件
     *
     * @param runnable
     */
    public static void post(Runnable runnable) {
        mMainHandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, long delay) {
        mMainHandler.postDelayed(runnable, delay);
    }

    public static void removeCallbacks(Runnable runnable) {
        mMainHandler.removeCallbacks(runnable);
    }

    public static void execute(Runnable task) {
        threadExecutor.execute(task);
    }

    public static Future<?> submit(Runnable task) {
        return threadExecutor.submit(task);
    }
}
