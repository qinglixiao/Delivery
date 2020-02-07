package me.std.flutterbridge.bridge.handlers;

import org.json.JSONObject;

import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.specs.BridgeResult;
import me.std.flutterbridge.bridge.specs.FlutterPageParameter;

/**
 * Created by Roger Huang on 2019/3/22.
 */

public class BridgeUpdatePageHandler extends BridgeHandler {
    @Override
    protected void onDestroy() {

    }

    @Override
    public String name() {
        return MethodSpec.FLT_REFRESH.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        if (arguments == null) {
            callback.onResult(null, BridgeResult.argumentRequired());
            return false;
        }

        FlutterPageParameter p = FlutterPageParameter.fromJson(arguments);

        if (p == null) {
            callback.onResult(null, BridgeResult.invalidArguments(arguments));
            return false;
        }

        if (context.bridgeView != null) {
            context.bridgeView.update(p);
        }

        return false;
    }
}
