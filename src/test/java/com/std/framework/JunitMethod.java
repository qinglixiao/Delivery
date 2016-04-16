package com.std.framework;

import com.library.util.DataConvert;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by gfy on 2016/4/14.
 */
public class JunitMethod {
    @Test
    public void test(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void parseXML(){
        DataConvert.toJSONString(new Object());
    }
}
