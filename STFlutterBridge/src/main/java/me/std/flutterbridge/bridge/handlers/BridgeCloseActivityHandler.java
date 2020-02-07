package me.std.flutterbridge.bridge.handlers;

import android.app.Activity;

import org.json.JSONObject;

import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-06-28.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class BridgeCloseActivityHandler extends BridgeHandler {
    @Override
    public String name() {
        return MethodSpec.NAT_CLOSE.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        ((Activity) context.context).finish();
        return true;
    }

    @Override
    protected void onDestroy() {

    }
}
