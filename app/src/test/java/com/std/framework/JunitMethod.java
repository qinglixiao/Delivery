package com.std.framework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.core.Reflect;
import com.std.framework.assist.JunitUtil;
import com.std.framework.util.AppUtil;
import com.std.framework.util.FingerUtil;

import junit.framework.Assert;

import org.junit.Test;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Gson gson = new Gson();

    @Test
    public void testFace() {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            JunitUtil.log("org:" + gson.hashCode() + "");
            Gson n1 = new Gson();
            JunitUtil.log("new:" + n1.hashCode() + "");
            TNPFaceRecommendListOutputForm result = (TNPFaceRecommendListOutputForm) (gson).fromJson("{\"data\":[],\"version\":1507542995000}", (new TypeToken<TNPFaceRecommendListOutputForm>() {
            }).getType());
            long time = System.currentTimeMillis() - start;
            JunitUtil.log(time + "");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReflect() {
        FingerUtil util = Reflect.on(FingerUtil.class).create().get();
        JunitUtil.print(util.hashCode());
        try {
            Class<?> clz = Class.forName(FingerUtil.class.getName());
            FingerUtil util1 = (FingerUtil) clz.getDeclaredConstructor().newInstance();
            JunitUtil.print(util1.hashCode());

            FingerUtil util2 = (FingerUtil) clz.newInstance();
            JunitUtil.print(util2.hashCode());

            Class[] type = new Class[1];
            type[0] = int.class;
            Class[] type1 = new Class[1];
            type[0] = String.class;
            Constructor<?> c1 = Class.forName(ReflectClazz.class.getName()).getDeclaredConstructor();
            Constructor<?> c2 = Class.forName(ReflectClazz.class.getName()).getConstructor();
            Constructor<?> c3 = Class.forName(ReflectClazz.class.getName()).getDeclaredConstructor(type);

            Reflect.on(ReflectClazz.class.getName()).create().call("del");
            JunitUtil.print("");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testString() {
        String src = "";
        src = src.replace("搜索小组", "好");
        JunitUtil.print(src);
    }

    public class TNPFaceRecommendListOutputForm implements Serializable {
        private List<TNPFaceShopOutputForm> data;
        private String version;

        public TNPFaceRecommendListOutputForm() {
        }

        public List<TNPFaceShopOutputForm> getData() {
            return this.data;
        }

        public void setData(List<TNPFaceShopOutputForm> data) {
            this.data = data;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public class TNPFaceShopOutputForm implements Serializable {
        private String faceBagId;
        private String name;
        private int price;
        public int totals;
        private int remain;
        private int status;
        public String lineTime;
        private int amount;
        private String slogan;
        private String intro;
        private String mark;
        private int orderBy;
        private String picId;
        private String picUrl;
        private String zipId;
        private String zipUrl;
        private int recommend;

        public TNPFaceShopOutputForm() {
        }

        public String getZipId() {
            return this.zipId;
        }

        public void setZipId(String zipId) {
            this.zipId = zipId;
        }

        public String getZipUrl() {
            return this.zipUrl;
        }

        public void setZipUrl(String zipUrl) {
            this.zipUrl = zipUrl;
        }

        public String getFaceBagId() {
            return this.faceBagId;
        }

        public void setFaceBagId(String faceBagId) {
            this.faceBagId = faceBagId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getRemain() {
            return this.remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getSlogan() {
            return this.slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getIntro() {
            return this.intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getMark() {
            return this.mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public int getOrderBy() {
            return this.orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public String getPicId() {
            return this.picId;
        }

        public void setPicId(String picId) {
            this.picId = picId;
        }

        public String getPicUrl() {
            return this.picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getRecommend() {
            return this.recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }
    }

    @Test
    public void testRegex() {
        // 按指定模式在字符串查找
        String line = "hell工o${CARD}晃悄烛光${fewafe}hytsf${8989}srgr一年${fe尿}的";
        String pattern = "\\$\\{\\w*[\\u4E00-\\u9FA5]*\\}";

        // 创建 Pattern 对象\
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);

        JunitUtil.log("match" + m.matches());

        JunitUtil.log(line.replaceFirst("\\$\\{(CARD)}", "名片"));
        while (m.find()) {
            String s = m.group();
            System.out.println("Found value: " + s);
            if (s.substring(2, s.length() - 1).equals("CARD")) {
                line = line.replace(s, "名片***");
            }
        }
        JunitUtil.log(line);

    }

    @Test
    public void testClassLoader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        FingerUtil one = (FingerUtil) Class.forName(FingerUtil.class.getName()).newInstance();
        FingerUtil fingerUtil = FingerUtil.class.newInstance();
        Field[] fields = ReflectClazz.class.getDeclaredFields();
        ReflectClazz reflectClazz = ReflectClazz.class.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            JunitUtil.log(field.get(reflectClazz).toString());
            if (field.getName().equals("age"))
                field.set(reflectClazz, 300);
        }
//        Reflect.on(One.class.getName()).create();
    }

    @Test
    public void testCalc() {
        int a = 7;
        int b = 2;
        int c = a ^ b;

        int FLAG_CALL_SUB_THREAD = 1;
        int FLAG_CALL_MAIN_THREAD = 1 << 1;
        int FLAG_RETURN_SUB_THREAD = 1 << 2;
        int FLAG_RETURN_MAIN_THREAD = 1 << 3;

        int flag = FLAG_RETURN_SUB_THREAD | FLAG_RETURN_MAIN_THREAD;

        JunitUtil.print(flag & FLAG_RETURN_SUB_THREAD);
    }

    @Test
    public void testGson(){
        System.out.println(new Gson().fromJson("afdsfad",String.class));
    }

}
