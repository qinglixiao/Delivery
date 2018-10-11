package com.std.framework;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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
public class XmlParse {
    @Test
    public void readXML() {
//        StringBuffer buffer = new StringBuffer();
//        try {
//            FileInputStream fileInputStream = new FileInputStream("D:\\personal\\STDFramework\\src\\test\\java\\com\\std\\framework\\assist\\case.xml");
//            InputStreamReader inputStream = new InputStreamReader(fileInputStream);
//            BufferedReader reader = new BufferedReader(inputStream);
//
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//
//            System.out.print(buffer.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        XStream stream = new XStream();
//        stream.processAnnotations(TestCase.class); //如果有注解则应用
////        stream.processAnnotations(TestCase.Case.class);
//
//        stream.addImplicitCollection(TestCase.class,"tests");
//        TestCase testCase1 = (TestCase) stream.fromXML(buffer.toString());

    }

    @Mock
    private List list;

    @Test
    public void testMock(){
        List list = Mockito.mock(List.class);
        Assert.assertNotNull(list);
    }
}
