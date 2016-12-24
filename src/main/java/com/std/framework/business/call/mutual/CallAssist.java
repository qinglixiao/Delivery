package com.std.framework.business.call.mutual;

import android.app.Activity;
import android.content.Intent;

import com.std.framework.business.call.view.activity.VoiceListActivity;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/12/22.
 * Modify by：lx
 */
public class CallAssist {
    public static void openRecordListActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, VoiceListActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
}
