package com.std.framework;

import com.google.gson.Gson;
import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.Resolve;
import com.std.framework.study.annotate.Input;
import com.std.framework.util.AppUtil;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/18.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class TestAnnotation {

    @Test
    public void testCall() {
        CYRouter.build("chunyu://lib/getApiLevel")
                .done(new Resolve<Integer>() {

                          @Override
                          public void call(Integer data) {
                              System.out.println(data);
                          }
                      }
                );

    }

    @Test
    public void testCallJson() {
        CYRouter.build("chunyu://lib/getApiLevel")
                .done(new Resolve<Integer>() {

                          @Override
                          public void call(Integer data) {
                              System.out.println(data);
                          }
                      }
                );
    }

    @Test
    public void testCallInt() {
        CYRouter.build("chunyu://login/toAD?type=10")
                .done(new Resolve<Integer>() {

                          @Override
                          public void call(Integer data) {
                              System.out.println(data);
                          }
                      }
                );
    }

    @Test
    public void testMap() {
        Map param = new HashMap();
        param.put("type1", 10);
        param.put("type", 2);
        CYRouter.build("chunyu://login/toAD", param)

                .done(new Resolve<Integer>() {

                          @Override
                          public void call(Integer data) {
                              System.out.println(data);
                          }
                      }
                        , new Capture() {
                            @Override
                            public void exception(Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        });

    }

    @Test
    public void testMap1() {
        Map param = new HashMap();
        param.put("path2", "path2");
        param.put("path1", "path1");
        CYRouter.build("chunyu://lib/mergePath", param)

                .done(new Resolve<String>() {

                          @Override
                          public void call(String data) {
                              System.out.println(data);
                          }
                      }
                );

    }

    @Test
    public void testMapP() {
        Map param = new HashMap();
        param.put("url1", "path2");
        param.put("url2", "path1");

        Map param2 = new HashMap();
        param2.put("key1", "key1");
        param2.put("key2", "key2");
        param.put("map", param2);
        CYRouter.build("chunyu://business/getMap", param)

                .done(new Resolve<String>() {

                          @Override
                          public void call(String data) {
                              System.out.println(data);
                          }
                      }
                );

    }

    @Test
    public void testMapJ() {
        Map param = new HashMap();
        param.put("url1", "path2");
        param.put("url2", "path1");

        Map param2 = new HashMap();
        param2.put("key1", "key1");
        param2.put("key2", "key2");
        param.put("map", new Gson().toJson(param2));
        CYRouter.build("chunyu://business/getHashMap", param)

                .done(new Resolve<String>() {

                          @Override
                          public void call(String data) {
                              System.out.println(data);
                          }
                      }
                );

    }

    @Test
    public void testList() {
        List param = new ArrayList();
        param.add("key1");
        param.add("key2");
        Map map = new HashMap();
        map.put("n", 100);
        map.put("arg", param);
        CYRouter.build("chunyu://business/list", map)

                .done(new Resolve<String>() {

                          @Override
                          public void call(String data) {
                              System.out.println(data);
                          }
                      }
                );

    }

    @Test
    public void testGene() {
        List<Input> param = new ArrayList();
        param.add(new Input());
        param.add(new Input());
        Map map = new HashMap();
        map.put("n", 100);
        map.put("arg", param);
        CYRouter.build("chunyu://business/listGene", map)
                .runOnSubThread()
                .returnOnMainThread()
                .done(new Resolve<List<Input>>() {

                    @Override
                    public void call(List<Input> data) {
                        System.out.println(data.toArray());
                    }

                });
    }

    @Test
    public void testKeyValue() throws UnsupportedEncodingException {
//        List<Input> param = new ArrayList();
//        param.add(new Input());
//        param.add(new Input());
//        CYRouter.build("chunyu://business/listGene"
//                , "n", 100
//                , "arg", new Gson().toJson(param))
//                .done(new Resolve<List<Input>>() {
//
//                    @Override
//                    public void call(List<Input> data) {
//                        System.out.println();
//                    }
//
//                }, new Capture() {
//                    @Override
//                    public void exception(Exception ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                });

    }


}
