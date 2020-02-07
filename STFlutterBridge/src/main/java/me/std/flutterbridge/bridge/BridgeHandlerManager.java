package me.std.flutterbridge.bridge;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import me.std.flutterbridge.bridge.handlers.BridgeCallNativeHandler;
import me.std.flutterbridge.bridge.handlers.BridgeCloseActivityHandler;
import me.std.flutterbridge.bridge.handlers.BridgeEventHandler;
import me.std.flutterbridge.bridge.handlers.BridgeEventSubscribeHandler;
import me.std.flutterbridge.bridge.handlers.BridgeGetInitArgsHandler;
import me.std.flutterbridge.bridge.handlers.BridgeHandler;
import me.std.flutterbridge.bridge.handlers.BridgeHttpHandler;
import me.std.flutterbridge.bridge.handlers.BridgeNewArchiveHandler;
import me.std.flutterbridge.bridge.handlers.BridgeOpenH5Handler;
import me.std.flutterbridge.bridge.handlers.BridgePopHandler;
import me.std.flutterbridge.bridge.handlers.BridgeUpdatePageHandler;
import me.std.flutterbridge.bridge.handlers.OpenFlutterHandler;
import me.std.flutterbridge.bridge.specs.BridgeResult;


/**
 * Created by Roger Huang on 2019/3/14.
 */

/*
 * 1. 每个flutter页面，有一个 BridgeHandlerManager，注册页面特有的handler
 * 2. globalManager用于注册通用的（每个页面都有的）handler
 */
public class BridgeHandlerManager extends FlutterBridgeObject {
    private Map<String, BridgeHandler> handlerMap = new HashMap<>();

    private static BridgeHandlerManager globalManager = new BridgeHandlerManager();

    public static BridgeHandlerManager getGlobalManager() {
        return globalManager;
    }

    private static BridgeHandlerManager nativeMethodManager = new BridgeHandlerManager();

    /*
     * 提供给native的方法，本质上也是一个BridgeHandler，只不过多了一层
     */
    public static BridgeHandlerManager getNativeMethodManager() {
        return nativeMethodManager;
    }

    static {
        // 默认的handlers

        globalManager.register(new OpenFlutterHandler());

        globalManager.register(new BridgeEventHandler());

        globalManager.register(new BridgeEventSubscribeHandler());

        globalManager.register(new BridgeGetInitArgsHandler());

        globalManager.register(new BridgeUpdatePageHandler());

        globalManager.register(new BridgeHttpHandler());

        globalManager.register(new BridgeCallNativeHandler());
    }

    static {
        nativeMethodManager.register(new BridgeNewArchiveHandler());
        nativeMethodManager.register(new BridgeOpenH5Handler());
        nativeMethodManager.register(new BridgeCloseActivityHandler());
        nativeMethodManager.register(new BridgePopHandler());
    }

    public BridgeHandlerManager() {

    }

    public boolean register(BridgeHandler handler) {
        if (handlerMap.containsKey(handler.name())) {
            return false;
        }

        handlerMap.put(handler.name(), handler);

        return true;
    }

    public void unregister(BridgeHandler handler) {
        handler = handlerMap.get(handler.name());
        if (handler != null) {
            handlerMap.remove(handler);
        }
    }

    public BridgeHandler getHandler(String name) {
        return handlerMap.get(name);
    }

    public boolean handle(FlutterBridgeContext context, MethodCall methodCall, final MethodChannel.Result result) {
        BridgeHandler handler = getHandler(methodCall.method);

        if (handler == null) {
            handler = getGlobalManager().getHandler(methodCall.method);
        }

        if (handler == null) {
            result.success(BridgeResult.make(null, BridgeResult.notSupportedMethod(methodCall.method)));
            return false;
        }

        return handler.handle(context, (JSONObject) methodCall.arguments, new BridgeHandler.Callback() {
            @Override
            public void onResult(Object data, Error error) {
                result.success(BridgeResult.make(data, error));
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (BridgeHandler h : handlerMap.values()) {
            h.destroy();
        }
    }
}
