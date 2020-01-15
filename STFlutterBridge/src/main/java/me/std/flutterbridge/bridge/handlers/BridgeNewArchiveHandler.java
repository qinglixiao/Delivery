package me.std.flutterbridge.bridge.handlers;

import android.app.Activity;
import android.content.Intent;

import org.json.JSONObject;

import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-06-25.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class BridgeNewArchiveHandler extends BridgeHandler {

    @Override
    public String name() {
        return MethodSpec.NAT_NEW_ARCHIVE.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        Intent intent = new Intent("me.chunyu.ChunyuIntent.ACTION_ADD_OR_MODIFY_NEW_PROFILE");
        String id = arguments.optString("id");
        String relation = arguments.optString("relation");
        intent.putExtra("id", id);
        intent.putExtra("relation", relation);
        int requestCode = arguments.optInt("request_code");
        ((Activity) context.context).startActivityForResult(intent, requestCode);
        return true;
    }

    @Override
    protected void onDestroy() {

    }
}
