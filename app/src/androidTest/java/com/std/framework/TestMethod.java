package com.std.framework;

import android.os.SystemClock;
import android.util.Log;

import com.google.gson.JsonArray;
import com.library.core.God;
import com.library.core.Reflect;
import com.library.util.SecurityUtil;
import com.std.framework.assist.Bean;
import com.std.framework.assist.JunitUtil;
import com.std.framework.business.call.view.fragment.CallFragment;
import com.std.framework.core.Logger;
import com.std.framework.util.AppUtil;
import com.std.framework.util.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Description:
 * Created by 李晓 ON 2017/11/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class TestMethod {
    private static final String TAG = "LX";

    @Test
    public void testformatStr() {
        String f_str = String.format("hello_%s%s", "wold", "你好");
        Logger.m(f_str);
    }

    public void testMD5() {
        String ss = "信息来源";
        Log.d("LX", SecurityUtil.SHA1.encrypt(ss));
        ss = "MD5加密";
        Log.d("LX", SecurityUtil.SHA1.encrypt(ss));
        Log.d("LX", SecurityUtil.MD5.encrypt(ss));
    }

    @Test
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
//        String phone = LibUtil.getPhoneNumber(getContext());
//        try {
//            Runtime.getRuntime().exec("input text fuytfrdrsxgf");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
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
//        String provider = About.getNetProvider(getContext());
    }

    @Test
    public void testSharedPreferences() {
        SharedPreferencesUtil.putUser("LX");
        assertEquals(SharedPreferencesUtil.getUser(), "LX");


    }

    public void testLogger() {
        Logger.PUT_OUT = true;
        Logger.m("logger_debug");
    }

    @Test
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
//        LibUtil.getPhoneNumber(getContext());
    }

    public void testIMEI() {
//        LibUtil.getDeviceIMEI(getContext());
    }

    public void testNetProvider() {
//        About.getNetProvider(getContext());
    }

    public void testFileUtil() {
//        File file = Environment.getExternalStorageDirectory();
//        Logger.m(file.getAbsolutePath());
//        Logger.m(file.getPath());
//        Logger.m(LibUtil.getAppDirectory(getContext()));
//        Logger.m(LibUtil.getDownLoadDirectory());
//        Logger.m(LibUtil.getCacheDirectory(getContext()));
//        FileUtil.delete(new File(LibUtil.getAppDirectory(getContext()) + File.separator + "io"), true);
//        File file1 = new File(LibUtil.getAppDirectory(getContext()));
//        for (File f : file1.listFiles()) {
//            Logger.m(f.getName());
//        }
    }

    public void testCreateFile() {
        String file = "/storage/emulated/0/宝宝家/voice/1482569331849.amr";
        try {
            File file1 = new File(file);
            if (file1.isFile()) {
                file1.getParentFile().mkdirs();
            }
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testMemery() {
        Logger.m(String.format("max:%d total:%d free:%d", AppUtil.getMaxMemoryAllocated() / 1024 / 1024, AppUtil.getTotalMemoryAllocated() / 1024 / 1024, AppUtil.getFreeMemoryAllocated() / 1024 / 1024));
    }

    @Test
    public void testParseJson() {
        String json = "{\n" +
                "    \"l1\": {\n" +
                "        \"l1_1\": [\n" +
                "            \"l1_1_1\",\n" +
                "            \"l1_1_2\"\n" +
                "        ],\n" +
                "        \"l1_2\": {\n" +
                "            \"l1_2_1\": 121\n" +
                "        }\n" +
                "    },\n" +
                "    \"l2\": {\n" +
                "        \"l2_1\": null,\n" +
                "        \"l2_2\": true,\n" +
                "        \"l2_3\": {}\n" +
                "    }\n" +
                "}";

        try {
            JSONObject object = new JSONObject(json);
            Iterator<String> keys = object.keys();
            StringBuffer buffer = new StringBuffer();
            while (keys.hasNext()) {
                String key = keys.next();
                object.get(key);
                buffer.append(key);
            }
            JunitUtil.print(buffer.toString());

        } catch (Exception e) {

        }
    }

    @Test
    public  String getDeviceBrand() {
        String brand = android.os.Build.BRAND;
        return brand;
    }

}
