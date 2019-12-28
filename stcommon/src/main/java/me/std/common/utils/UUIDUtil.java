package me.std.common.utils;

import android.text.TextUtils;

import java.util.UUID;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-28.e
 */
public class UUIDUtil {

    public static String generate(String name) {
        if (TextUtils.isEmpty(name)) {
            return random();
        }
        return SecurityUtil.MD5.encrypt(name).replace(":","");
    }

    public static String random() {
        return UUID.randomUUID().toString();
    }
}
