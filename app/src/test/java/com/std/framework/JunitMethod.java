package com.std.framework;

import com.google.gson.Gson;
import com.library.core.Reflect;
import com.std.framework.assist.JunitUtil;
import com.std.framework.util.AppUtil;

import junit.framework.Assert;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * Created by 李晓 ON 2017/11/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class JunitMethod {
    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testURL() {
        String url = "http://t100.beacon.toon.mobi/init/F36804BD/05/null/null/null/最浪漫/null/39.995118365575514/116.45189493369065/c_1444943678030945/true/true/指派/北京市朝阳区望京西路316号附近/c_1444943678030945?toonKey={\"authKey\":{\"appVersion\":\"3.1.5\",\"deviceId\":\"A000004F64D3FF\",\"platform\":\"android\",\"platformVersion\":\"19\",\"ticket\":\"BCFE59DD6DB2ED64D7AE3DCD65D8159D\",\"userId\":\"301801\",\"userToken\":\"a80e861c-5c63-4fa8-a969-aa899fa32785\"}}";
        String ur = "";
        try {
            try {
                ur = new URL(url).getPath();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            url = URLEncoder.encode(ur, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void m() {
        long s = Long.parseLong("F36804BD", 16);
        int a = (int) s;
    }

    @Test
    public void GsonMap() {
        HashMap<String, String> param = new HashMap<>();
        param.put("userId", "LX");
        param.put("userName", "李晓");
        param.put("token", "resee235jfoiexoeor");
        JunitUtil.log(new Gson().toJson(param));
    }

    @Test
    public void testClone() {
        class People implements Cloneable {
            @Override
            public Object clone() {
                People people = null;
                try {
                    people = (People) super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return people;
            }
        }

        People people1 = new People();
        JunitUtil.log(people1.hashCode() + "");
        JunitUtil.log(people1.clone().hashCode() + "");

    }

    @Test
    public void testEnum() {
        A a = A.valueOf(B.Choole.name());
        JunitUtil.print(RichEditFuntion.TEXT.ordinal());
        JunitUtil.print(RichEditFuntion.VIDEO.ordinal());
        JunitUtil.print(RichEditFuntion.IMAGE.ordinal());

        JunitUtil.print(RichEditFuntion.TEXT.getValue());
        JunitUtil.print(RichEditFuntion.VIDEO.getValue());
        JunitUtil.print(RichEditFuntion.IMAGE.getValue());

        JunitUtil.print(RichEditFuntion.IMAGE.name());
    }

    enum A {
        Choole,
        Home,
        Me
    }

    enum B {
        Choole,
        Home,
        Me
    }

    @Test
    public void swap() {
        int a = 10, b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        JunitUtil.log("a = " + a + " b = " + b);
    }

    @Test
    public void testProcess() {
        int count = Runtime.getRuntime().availableProcessors();
        JunitUtil.log(count + "");
    }

    @Test
    public void testMemery() {
        AppUtil.getTotalMemoryAllocated();
        AppUtil.getFreeMemoryAllocated();
        JunitUtil.log(String.format("max:%d total:%d free:%d", AppUtil.getMaxMemoryAllocated() / 1024 / 1024, AppUtil.getTotalMemoryAllocated() / 1024 / 1024, AppUtil.getFreeMemoryAllocated() / 1024 / 1024));
    }

    @Test
    public void testRef() {
        One one = new One();
        one.set(new Three());
        Three three = new Three();
        three.age = 20;
        three.name = "me";
        one.c = three;
        JunitUtil.log(one.b.c.age + "  " + one.b.c.name);
        JunitUtil.log(one.c.age + "  " + one.c.name);
    }

    class One {
        Three c;
        Two b;

        public One() {
            b = new Two();
        }

        public void set(Three c) {
            this.c = c;
            b.setC(c);
        }
    }

    class Two {
        Three c;

        public void setC(Three c) {
            this.c = c;
            c = null;
        }

    }

    class Three {
        int age = 10;
        String name = "lx";
    }

    @Test
    public void reflect() {
        JunitMethod junitMethod = Reflect.on("com.std.framework.JunitMethod").create().get();
    }

    @Test
    public void arraysCopy() {
        List<String> e = Arrays.asList("你好", "朋友");
        JunitUtil.log(Arrays.toString(e.toArray()));
        List<String> copy = new ArrayList<>(e);
        JunitUtil.log(Arrays.toString(copy.toArray()));
    }

    @Test
    public void testParseInteger() {
        String i = "-1";
        JunitUtil.log(Integer.parseInt(i) + "");
    }

    @Test
    public void testArray() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        int code = 100011;
        long start = System.nanoTime();
        int index = Arrays.binarySearch(array, 0);
//        if (
//                code == 100001
//                || code == 100008
//                || code == 100009
//                || code == 100007
//                || code == 100011
//                || code == 100004
//                || code == 100012
//                || code == 100006
//                || code == 100010
//                )
        JunitUtil.log("index:" + index + " time:" + (System.nanoTime() - start));
    }

    public enum RichEditFuntion {
        TEXT(1),
        IMAGE(2),
        VIDEO(3),
        VOICE(4),
        MAP(5);

        int value;

        public int getValue() {
            return value;
        }

        RichEditFuntion(int value) {
            this.value = value;
        }
    }

    @Test
    public void testMath() {
        long n = 3400;


        int t = (int) Math.ceil(3400 / 1000f);
//        int t = Math.floor( 3400 / 1000);
        JunitUtil.log(t + "");
    }

}
