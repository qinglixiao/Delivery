package com.std.framework.assist;

import me.std.common.utils.Logger;

/**
 * Description:
 * Created by 李晓 ON 2017/11/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class JunitUtil {
    public static void log(String info) {
        System.out.println(info);
    }

    public static void print(String info) {
        Logger.d("LX", info);
    }

    public static void print(int info) {
        System.out.println(info);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
        }

    }
}
