package me.std.flutterbridge.bridge.event;

import android.text.TextUtils;

import org.json.JSONObject;

import me.std.common.core.DataConvert;
import me.std.flutterbridge.bridge.event.BridgeEventCenter;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.handlers.BridgeHandler;
import me.std.flutterbridge.bridge.specs.BridgeResult;

/*
 * flutter向原生发送事件
 * 使用场景： 打开flutter页面前，监听事件, flutter页面可以通过事件向当前页面发送消息
 */
public class BridgeEventHandler extends BridgeHandler {
    public static class Event {
        public boolean isValid() {
            return !TextUtils.isEmpty(name);
        }

        public String name;
        public String source;
        public Object data;
        public String error;
        public FlutterBridgeContext bridgeContext;

        public Error getError() {
            return error == null ? null : new Error(error);
        }
    }

    @Override
    public String name() {
        return "flutter_event";
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        if (arguments == null) {
            callback.onResult(null, BridgeResult.argumentRequired());
            return false;
        }

        Event event = DataConvert.fromJson(arguments.toString(), Event.class);
        event.bridgeContext = context;
        if (!event.isValid()) {
            callback.onResult(null, BridgeResult.invalidArguments(arguments));
            return false;
        }

        BridgeEventCenter.getInstance().emit(event);

        callback.onResult(null, null);

        return true;
    }

    @Override
    protected void onDestroy() {

    }
}
