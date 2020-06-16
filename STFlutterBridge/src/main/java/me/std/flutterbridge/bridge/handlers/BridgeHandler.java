package me.std.flutterbridge.bridge.handlers;

import org.json.JSONObject;

import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.FlutterBridgeObject;

public abstract class BridgeHandler extends FlutterBridgeObject {
    public interface Callback {
        void onResult(Object data, Error error);
    }
    public abstract String name();
    public abstract boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback);
}
