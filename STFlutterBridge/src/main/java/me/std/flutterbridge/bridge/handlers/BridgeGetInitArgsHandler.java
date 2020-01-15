package me.std.flutterbridge.bridge.handlers;

import org.json.JSONObject;

import me.chunyu.flutter.bridge.FlutterBridgeContext;

/**
 * Created by Roger Huang on 2019/3/22.
 */

public class BridgeGetInitArgsHandler extends BridgeHandler {
    @Override
    protected void onDestroy() {

    }

    @Override
    public String name() {
        return "get_init_args";
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {

        callback.onResult(context.pageInitArgs, null);

        return true;
    }
}
