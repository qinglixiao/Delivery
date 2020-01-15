package me.std.flutterbridge.bridge;

import android.content.Context;

/**
 * Created by Roger Huang on 2019/3/16.
 */

public class FlutterBridgeContext {
    public FlutterBridgeContext(Context context, BridgeInvoker invoker) {
        this.context = context;
        this.invoker = invoker;
    }

    public Context context;
    public BridgeInvoker invoker;
    public Object pageInitArgs;
    public IBridgeView bridgeView;
}
