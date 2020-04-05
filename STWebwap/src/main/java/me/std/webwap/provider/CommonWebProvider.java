package me.std.webwap.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.std.framework.annotation.RouterModule;
import com.std.framework.annotation.RouterPath;

import me.std.webwap.comm.H5Args;
import me.std.webwap.view.activity.CommonWebActivity;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/23.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@RouterModule(schema = "chunyu", host = "CommonWebProvider")
public class CommonWebProvider {

    @RouterPath(value = "/openH5")
    public void openH5(Context activity, String url, String title, int requestCode) {
        Intent intent = new Intent(activity, CommonWebActivity.class);
        intent.putExtra(H5Args.ARG_WEB_TITLE, title);
        intent.putExtra(H5Args.ARG_WEB_URL, url);
        if (activity instanceof Activity) {
            ((Activity) activity).startActivityForResult(intent, requestCode);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }
}
