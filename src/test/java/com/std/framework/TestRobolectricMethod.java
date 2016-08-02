package com.std.framework;

import android.content.Context;

import com.library.util.LibUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

/**
 * Description :
 * Created by lx on 2016/7/22.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
@RunWith(RobolectricTestRunner.class)
public class TestRobolectricMethod {
    private Context context;

    @Before
    public void setUp() throws Exception {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void testLibPhone(){
        LibUtil.getPhoneNumber(context);
    }
}
