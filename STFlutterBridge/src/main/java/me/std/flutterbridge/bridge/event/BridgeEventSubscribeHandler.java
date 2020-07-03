package me.std.flutterbridge.bridge.event;

import android.text.TextUtils;

import org.json.JSONObject;

import me.std.common.core.DataConvert;
import me.std.flutterbridge.bridge.FlutterInvoker;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.handlers.BridgeHandler;
import me.std.flutterbridge.bridge.specs.BridgeCallbackSpec;
import me.std.flutterbridge.bridge.specs.BridgeResult;

/**
 * flutter订阅事件
 */
public class BridgeEventSubscribeHandler extends BridgeHandler implements BridgeEventCenter.OnEventListener {

    static class Parameter {
        public boolean isValid() {
            return !TextUtils.isEmpty(name) &&
                    (callback != null && !TextUtils.isEmpty(callback.method));
        }
        public String name;
        public String source;
        public BridgeCallbackSpec callback;
        public boolean unsubscribe;
    }

    @Override
    public String name() {
        return "flutter_observe";
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        if (arguments == null) {
            callback.onResult(null, BridgeResult.argumentRequired());
            return false;
        }

        Parameter parameter =  DataConvert.fromJson(arguments.toString(), Parameter.class);
        if (!parameter.isValid()) {
            callback.onResult(null, BridgeResult.invalidArguments(arguments));
            return false;
        }

        this.invoker = context.invoker;

        if (parameter.unsubscribe) {
            subscribeManager.unsubscribe(parameter.name, parameter.callback.method);

            if (!subscribeManager.hasSubscriber(parameter.name)) {
                BridgeEventCenter.getInstance().unsubscribe(parameter.name, this);
            }
        } else {
            if (!subscribeManager.hasSubscriber(parameter.name)) {
                BridgeEventCenter.getInstance().subscribe(parameter.name, this);
            }

            subscribeManager.subscribe(parameter.name, parameter.callback.method);
        }

        callback.onResult(null, null);

        return true;
    }

    @Override
    public void onEvent(BridgeEventHandler.Event event) {
        for (String callback: subscribeManager.getSubscribers(event.name)) {
            invoker.invoke(callback, BridgeResult.make(event.data, event.getError()), new FlutterInvoker.Callback() {
                @Override
                public void onResult(Object object, Error error) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        BridgeEventCenter.getInstance().unsubscribe(this);
        this.invoker = null;
    }

    private BridgeEventSubscribeManager<String> subscribeManager = new BridgeEventSubscribeManager();

    private FlutterInvoker invoker;
}
