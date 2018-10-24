package com.std.framework.business.explore.view.activity;

import android.app.Activity;
import android.content.Intent;

import com.std.framework.annotation.RouterModule;
import com.std.framework.annotation.RouterPath;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/24.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@RouterModule(schema = "chunyu", host = "main")
public class MainModule {

    @RouterPath(value = "/toMain")
    public void openMain(Activity activity, int request) {
        activity.startActivityForResult(new Intent(activity, DemoRouterActivity.class), request);
    }
}
