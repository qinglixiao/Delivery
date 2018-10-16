package com.std.annotation;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

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
    public void TestUri() {
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
}
