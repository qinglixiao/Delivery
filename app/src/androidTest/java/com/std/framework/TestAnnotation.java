package com.std.framework;

import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Capture;
import com.std.framework.router.interfaces.Resolve;
import com.std.framework.util.AppUtil;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
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
    @Before
    public void init() {
        CYRouter.init(AppUtil.getAppContext());
    }

    @Test
    public void testCall() {
        CYRouter.open("chunyu://lib/getApiLevel")
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
        CYRouter.open("chunyu://lib/getApiLevel")
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
        CYRouter.open("chunyu://login/toAD?type=10")
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
        CYRouter.open("chunyu://login/toAD", param)

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
        param.put("path2","path2");
        param.put("path1","path1");
        CYRouter.open("chunyu://lib/mergePath", param)

                .done(new Resolve<String>() {

                          @Override
                          public void call(String data) {
                              System.out.println(data);
                          }
                      }
                );

    }

}
