package com.std.framework;

import android.os.Environment;
import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.util.Log;

import com.library.core.God;
import com.library.core.Reflect;
import com.library.util.About;
import com.library.util.FileUtil;
import com.library.util.LibUtil;
import com.library.util.SecurityUtil.MD5;
import com.library.util.SecurityUtil.SHA1;
import com.std.framework.assist.Bean;
import com.std.framework.basic.App;
import com.std.framework.business.find.view.fragment.EventBusFragment;
import com.std.framework.core.Logger;
import com.std.framework.business.call.view.fragment.CallFragment;
import com.std.framework.util.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;


public class TestMethod extends AndroidTestCase {
    private static final String TAG = "LX";

    public void testformatStr() {
        String f_str = String.format("hello_%s%s", "wold", "你好");
        Logger.m(f_str);
    }


    public void testMD5() {
        String ss = "信息来源";
        Log.d("LX", SHA1.encrypt(ss));
        ss = "MD5加密";
        Log.d("LX", SHA1.encrypt(ss));
        Log.d("LX", MD5.encrypt(ss));
    }


    public void movebit() {
        int MODE_SHIFT = 30;
        int MODE_MASK = 3 << MODE_SHIFT;
        int left = MODE_SHIFT << 3;
        int right = 3 >> 2;
        Log.d("LX", MODE_MASK + "");
        Log.d("LX", left + "");
        Log.d("LX", right + "");
    }

    public void testMemory() {
        long size = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        Log.d(TAG, size + "");
    }

    public void testPhoneNumber() {
        String phone = LibUtil.getPhoneNumber(getContext());
        try {
            Runtime.getRuntime().exec("input text fuytfrdrsxgf");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testRuntime() {
        int pro = Runtime.getRuntime().availableProcessors();
        Log.d(TAG, pro + "");
    }

    public void testReflected() {
        long start = SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < 100; i++) {
            new CallFragment();
        }
        long second = SystemClock.currentThreadTimeMillis();
        Log.d(TAG, "new time :" + (second - start));
        for (int i = 0; i < 100; i++) {
            God.love(CallFragment.class);
        }
        long third = SystemClock.currentThreadTimeMillis() - second;
        Log.d(TAG, "reflect time :" + third);

        CallFragment fourFragment = Reflect.on(CallFragment.class).create().get();
        assertNotNull(fourFragment);

        Bean bean = Reflect.on(Bean.class).create(30, "").get();
        assertNotNull(bean);
        assertEquals(bean.id, 30);
    }

    public void testPrivider() {
        String provider = About.getNetProvider(getContext());
    }

    public void testSharedPreferences() {
        SharedPreferencesUtil.putUser("LX");
        assertEquals(SharedPreferencesUtil.getUser(), "LX");
    }

    public void testLogger() {
        Logger.PUT_OUT = true;
        Logger.m("logger_debug");
    }

    public void testJson() {
        String json = "{\"code\":500,\"message\":\"指定的审批人不是设备管理员\"}";
        JSONTokener jsonTokener = new JSONTokener(json);
        try {
            JSONObject object = (JSONObject) jsonTokener.nextValue();
            int error = object.getInt("code");
            Logger.m(error + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testLibPhone() {
        LibUtil.getPhoneNumber(getContext());
    }

    public void testIMEI() {
        LibUtil.getDeviceIMEI(getContext());
    }

    public void testNetProvider() {
        About.getNetProvider(getContext());
    }

    public void testFileUtil(){
        File file = Environment.getExternalStorageDirectory();
        Logger.m(file.getAbsolutePath());
        Logger.m(file.getPath());
        Logger.m(LibUtil.getAppDirectory(getContext()));
        Logger.m(LibUtil.getDownLoadDirectory());
        Logger.m(LibUtil.getCacheDirectory(getContext()));
        FileUtil.delete(new File(LibUtil.getAppDirectory(getContext()) + File.separator + "io"),true);

//        FileUtil.deleteCascade(file);
    }

}
