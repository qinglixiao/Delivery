package me.std.common.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-28.
 */
public class FileUtilTest {

    @Before
    public void init() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AppContextUtil.initApp(appContext);
    }

    @Test
    public void getImageCacheFile() {
        File file = FileUtil.getImageCacheFile();
    }

    @Test
    public void createImageCacheFile() {
        FileUtil.newImageCacheFile("wq");
    }
}