package me.std.webwap.mutual;

import android.app.Activity;
import android.content.Intent;

import com.std.framework.router.CYRouter;

import java.util.HashMap;
import java.util.Map;

import me.std.common.core.ObjectArgsParser;
import me.std.webwap.view.activity.CommonWebActivity;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/22.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CommonWebAssistant {

    public static void openWebActivity(Activity activity, Object... params) {
        Intent intent = ObjectArgsParser.toIntent(params);
        intent.setClass(activity, CommonWebActivity.class);
        activity.startActivity(intent);
    }

    public static void openWebActivity(Activity activity, Map params) {
        Intent intent = ObjectArgsParser.toIntent(params);
        intent.setClass(activity, CommonWebActivity.class);
        activity.startActivity(intent);
    }

    public static void openWebActivity(Activity activity, String url, String title, int requestCode) {
        Intent intent = new Intent(activity, CommonWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void openSelFriendActivity(Activity activity, String messageDataInfo, String messageDataContent, String messageDataType) {
        Map param = new HashMap();
        param.put("activity", activity);
        param.put("messageDataInfo", messageDataInfo);
        param.put("messageDataContent", messageDataContent);
        param.put("messageDataType", messageDataType);
        CYRouter.build("chunyu://WorkbenchProvider/choose_friend", param)
                .returnOnMainThread()
                .done();
    }
}
