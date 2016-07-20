package com.std.framework;

import com.google.gson.Gson;
import com.std.framework.assist.JunitUtil;
import com.std.framework.basic.App;
import com.std.framework.util.AppUtil;
import com.std.framework.util.Logger;

import junit.framework.Assert;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by gfy on 2016/4/14.
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
    public void testClone(){
        class People implements Cloneable{
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
        JunitUtil.log(people1.hashCode()+"");
        JunitUtil.log(people1.clone().hashCode()+"");

    }

    @Test
    public void testEnum(){
        A a = A.valueOf(B.Choole.name());
    }

    enum A{
        Choole,
        Home,
        Me
    }

    enum B{
        Choole,
        Home,
        Me
    }

    @Test
    public void swap(){
        int a = 10,b = 20;
        a = a^b;
        b = a^b;
        a = a^b;
        JunitUtil.log("a = "+a +" b = "+b);
    }

    @Test
    public void testProcess(){
        int count = Runtime.getRuntime().availableProcessors();
        JunitUtil.log(count + "");
    }

    @Test
    public void testMemery(){
        AppUtil.getTotalMemoryAllocated();
        AppUtil.getFreeMemoryAllocated();
        JunitUtil.log(String.format("max:%d total:%d free:%d",AppUtil.getMaxMemoryAllocated()/1024/1024,AppUtil.getTotalMemoryAllocated()/1024/1024, AppUtil.getFreeMemoryAllocated()/1024/1024));
    }

    @Test
    public void testMeta(){
        JunitUtil.log(AppUtil.getMetaData("com.baidu.lbsapi.API_KEY"));
    }

}
