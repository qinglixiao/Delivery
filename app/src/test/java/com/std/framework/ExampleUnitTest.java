package com.std.framework;

import com.library.core.ThreadPool;
import com.library.util.LogUtil;
import com.std.framework.assist.JunitUtil;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        JunitUtil.print(ThreadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 10;
            }
        }).get());
    }
}