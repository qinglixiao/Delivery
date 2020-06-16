package me.std.flutterbridge.bridge;

import android.content.Context;

public class FlutterBridgeContext {
    public FlutterBridgeContext(Context context, FlutterInvoker invoker) {
        this.context = context;
        this.invoker = invoker;
    }

    public Context context;
    public FlutterInvoker invoker;
    public Object pageInitArgs;
    public IBridgeView bridgeView;
}
