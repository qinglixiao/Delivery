package me.std.common.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-29.
 */
public class VersionInfoTest {

    @Test
    public void compare() {
        String version1 = "2.0.1.1";
        String version2 = "2";

        Assert.assertEquals(VersionInfo.compare(version1,version2),1);
    }
}