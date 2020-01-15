package me.std.flutterbridge.bridge.handlers;

import org.json.JSONObject;

import java.util.Map;

import me.std.common.core.DataConvert;
import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.BridgeHandlerManager;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.specs.BridgeCallbackSpec;
import me.std.flutterbridge.bridge.specs.BridgeParameterResolver;
import me.std.flutterbridge.bridge.specs.BridgeResult;

/**
 * Created by Roger Huang on 2019/3/18.
 */

public class BridgeCallNativeHandler extends BridgeHandler {
    @Override
    protected void onDestroy() {

    }

    @Override
    public String name() {
        return MethodSpec.NAT_CALL.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        BridgeParameterResolver resolver = new BridgeParameterResolver(arguments);

        BridgeCallbackSpec callSpec = DataConvert.parseFromJson(arguments.toString(), BridgeCallbackSpec.class);

        BridgeHandler handler = BridgeHandlerManager.getNativeMethodManager().getHandler(callSpec.method);

        if (handler == null) {
            callback.onResult(null, BridgeResult.notSupportedMethod(callSpec.method));
            return false;
        }

        handler.handle(context, callSpec.parameters != null ? new JSONObject((Map) callSpec.parameters) : new JSONObject(), callback);

        return true;
    }
}
