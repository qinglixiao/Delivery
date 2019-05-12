package com.std.framework;

import com.library.core.ThreadPool;
import com.std.framework.assist.JunitUtil;

import org.junit.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


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
//        JunitUtil.print(ThreadPool.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 10;
//            }
//        }).get());
    }

    @Test
    public void testNum() {
//        assertEquals(36 % 2, 0);
        Object a;
        a = System.currentTimeMillis();
        JunitUtil.print(a.getClass().getSimpleName());
    }

    @Test
    public void testList() {
        List args = new ArrayList();
        args.add("args");
        args.add(20);
        args.add("arg2");
        args.add(40);
        print(args);
    }

    private void print(Object... args) {
        if (args != null) {
            for (Object arg : args)
                JunitUtil.print(arg.toString());
        }
//        assertEquals(args,null);
//        assertEquals(args.length,0);
    }

    @Test
    public void testNull() {
        print(new Object[0]);
    }

    @Test
    public void testMock() {
        List list = mock(List.class);
        list.add(1);
        verify(list).add(2);
    }

    @Test
    public void testType() {
        Define define = new Define();
        Serializable.class.isAssignableFrom(define.getClass());
    }

    public static class Define implements A {

    }

    interface A extends Serializable {

    }

}