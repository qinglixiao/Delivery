package com.std.framework.assist;

/**
 * Created by Administrator on 2016/6/11.
 */
public class JunitUtil {
    public static void log(String info){
        System.out.println(info);
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        }catch (Exception ex){
        }

    }
}
