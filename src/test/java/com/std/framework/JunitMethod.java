package com.std.framework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.std.framework.assist.BCNetBean;
import com.std.framework.assist.Case;
import com.std.framework.assist.NetLockKey;
import com.std.framework.assist.TestCase;
import com.thoughtworks.xstream.XStream;

import junit.framework.Assert;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by gfy on 2016/4/14.
 */
public class JunitMethod {
    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void parseXML() {
//        DataConvert.toXMLString(new TestCase.Data("LX", "fsadfdf"));
    }

    @Test
    public void readXML() {
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\personal\\STDFramework\\src\\test\\java\\com\\std\\framework\\assist\\case.xml");
            InputStreamReader inputStream = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStream);

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            System.out.print(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XStream stream = new XStream();
        stream.processAnnotations(TestCase.class); //如果有注解则应用
//        stream.processAnnotations(TestCase.Case.class);

        stream.addImplicitCollection(TestCase.class,"tests");
        TestCase testCase1 = (TestCase) stream.fromXML(buffer.toString());

    }

    @Test
    public void newBeanXml(){
        TestCase testCase = new TestCase();
        testCase.classname = "parent";
        Case one = new Case();
        one.isRun = "false";
        one.name="first";
        Case two = new Case();
        two.isRun = "false";
        two.name="two";
        testCase.tests.add(one);
        testCase.tests.add(two);

        XStream stream = new XStream();
//        stream.processAnnotations(TestCase.class); //如果有注解则应用
//        stream.alias("testcase", TestCase.class);
//        stream.alias("tests", Case.class);
        stream.addImplicitCollection(TestCase.class,"tests");
        stream.autodetectAnnotations(true);

        String xml = stream.toXML(testCase);
        System.out.println(xml);

        TestCase testCase1 = (TestCase) stream.fromXML(xml);

//        TestCase testCase1 = (TestCase) stream.fromXML(xml);

//        HashMap<String,String> field = new HashMap<>();
//        field.put("test", List<TestCase.Case.class>.getSimpleName().toLowerCase());
//        TestCase testCase1 = DataConvert.parseObjectFromXML(xml,TestCase.class,field);
    }

    @Test
    public void testCompare(){
        String s = "{\"code\":0,\"results\":[{\"feedID\":\"c_1458444273295315\",\"created_at\":1460688472000,\"expiredTime\":0,\"toonBeaconSetting\":{\"tBeaconID\":4083743752,\"tBeaconType\":1,\"tBeaconUUID\":\"1-0-c_1845627617289308-4083743752\",\"tBeaconAdminPWD\":92606040,\"tBeaconTitle\":\"å\u0093\u0088å\u0093\u0088å\u0093\u0088å\u0093\u0088\"}},{\"feedID\":\"c_1458444273295315\",\"created_at\":1460688472000,\"expiredTime\":0,\"toonBeaconSetting\":{\"tBeaconID\":4083743752,\"tBeaconType\":1,\"tBeaconUUID\":\"1-0-c_1845627617289308-4083743752\",\"tBeaconAdminPWD\":92606040,\"tBeaconTitle\":\"å\u0093\u0088å\u0093\u0088å\u0093\u0088å\u0093\u0088\"}}]}";
        String code1 = "{\"code\":0";
        Assert.assertTrue(s.startsWith(code1));
    }

    @Test
    public void testGson(){
        String json = "{\"code\":0,\"results\":[{\"feedID\":\"c_1458444273295315\",\"created_at\":1460688472000,\"expiredTime\":0,\"toonBeaconSetting\":{\"tBeaconID\":4083743752,\"tBeaconType\":1,\"tBeaconUUID\":\"1-0-c_1845627617289308-4083743752\",\"tBeaconAdminPWD\":92606040,\"tBeaconTitle\":\"å\u0093\u0088å\u0093\u0088å\u0093\u0088å\u0093\u0088\"}},{\"feedID\":\"c_1458444273295315\",\"created_at\":1460688472000,\"expiredTime\":0,\"toonBeaconSetting\":{\"tBeaconID\":4083743752,\"tBeaconType\":1,\"tBeaconUUID\":\"1-0-c_1845627617289308-4083743752\",\"tBeaconAdminPWD\":92606040,\"tBeaconTitle\":\"å\u0093\u0088å\u0093\u0088å\u0093\u0088å\u0093\u0088\"}}]}";
        BCNetBean<List<NetLockKey>> result = new Gson().fromJson(json,
                new TypeToken<BCNetBean<List<NetLockKey>>>() {
                }.getType());

    }

}
