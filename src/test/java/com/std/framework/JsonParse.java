package com.std.framework;

import com.google.gson.Gson;
import com.std.framework.assist.JunitUtil;

import org.junit.Test;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/7/11.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class JsonParse {

    @Test
    public void testParse(){
        Company  company = new Company();
        company.name = "cc";
        company.address="aa";
        company.title = "tt";
        JunitUtil.log(new Gson().toJson(company));
    }
}

class Company{
    String name;
    String address;
    String title;
}
