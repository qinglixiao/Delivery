package me.std.flutterbridge.bridge.handlers;

import android.app.Activity;

import org.json.JSONObject;

import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.specs.BridgeResult;

/**
 * Created by Roger Huang on 2019/3/16.
 * pop flutter页面（flutter自己的navigation）
 */

public class BridgePopHandler extends BridgeHandler {
    @Override
    protected void onDestroy() {

    }

    @Override
    public String name() {
        return MethodSpec.FLT_GO_BACK.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        if (context.context instanceof Activity) {
            Activity activity = (Activity)context.context;
            activity.onBackPressed();

            callback.onResult(null, null);

            return true;
        }

        callback.onResult(null, BridgeResult.methodFailed(name(), ""));

        return false;
    }
}
