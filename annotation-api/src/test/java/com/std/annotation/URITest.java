package com.std.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class URITest {
    @Test
    public void testUri() {
        try {
            URI uri = new URI("android://login/path?param='1'&param2=2");
            String schema = uri.getScheme();
            String host = uri.getHost();
            String path = uri.getPath();
            String rawPath = uri.getRawPath();
            String query = uri.getQuery();
            String rawQuery = uri.getQuery();

            StringBuffer buffer = new StringBuffer();
            buffer.append(schema);
            buffer.append("\n");
            buffer.append(host);
            buffer.append("\n");
            buffer.append(path);
            buffer.append("\n");
            buffer.append(rawPath);
            buffer.append("\n");
            buffer.append(query);
            buffer.append("\n");
            buffer.append(rawQuery);

            System.out.print(buffer.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testString(){
        String str1 = "ABcD";
        String str2 = "abcd";
        Assert.assertTrue(str1.equalsIgnoreCase(str2));
    }

    @Test
    public void testMap(){
        Object map = new HashMap();
        ((HashMap) map).put("arg1",1);
        ((HashMap) map).put("arg2",2);
        System.out.println(map);

    }

}