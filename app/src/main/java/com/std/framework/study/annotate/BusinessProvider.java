package com.std.framework.study.annotate;

import android.app.Activity;

import com.std.framework.annotation.RouterModule;
import com.std.framework.annotation.RouterPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/17.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@RouterModule(schema = "chunyu", host = "business")
public class BusinessProvider {

    @RouterPath(value = "/mm")
    public void mm(Integer n, String... arg) {

    }

    @RouterPath(value = "/list")
    public void mm(Integer n, List arg) {

    }

    @RouterPath(value = "/openContract")
    public void openContract(Activity activity, int requestCode) {

    }

    @RouterPath(value = "/openGesture")
    public void openGestureActivity(Activity activity, String from, String index, int requestCode) {

    }

    @RouterPath(value = "/getUrl")
    public String getUrl(String url1, String url2) {
        return url1 + url2;
    }

    @RouterPath(value = "/getMap")
    public String getMap(String url1, String url2, Map map) {
        return url1 + url2;
    }

    @RouterPath(value = "/getHashMap")
    public String getHashMap(String url1, String url2, HashMap<String,Object> map) {
        return url1 + url2;
    }


}
