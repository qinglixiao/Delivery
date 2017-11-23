package com.std.framework.util;

import android.widget.Toast;

import com.std.framework.basic.App;

/**
 * Description:
 * Created by 李晓 ON 2016/12/8.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader:
 */
public class ToastUtil {

    public static void show(String message) {
        Toast.makeText(AppUtil.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }
}
