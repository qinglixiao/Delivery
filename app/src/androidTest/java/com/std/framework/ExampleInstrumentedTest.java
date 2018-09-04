package com.std.framework;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.library.core.ThreadPool;
import com.std.framework.assist.JunitUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * Description:
 * Created by 李晓 ON 2017/12/23.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.d("LX",appContext.getPackageName());
//        JunitUtil.log(appContext.getPackageName());
//        assertEquals("trends.systoon.com.myapplication", appContext.getPackageName());
    }
    @Test
    public void testThreadPool(){
        CountDownLatch latch = new CountDownLatch(1);
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                JunitUtil.print("执行线程任务...");
                latch.countDown();
            }
        });
        try {
            latch.await();
            JunitUtil.print("任务执行完成!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
