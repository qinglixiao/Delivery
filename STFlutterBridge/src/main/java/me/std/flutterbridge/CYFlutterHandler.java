package me.std.flutterbridge;

import me.std.common.utils.Logger;
import me.std.flutterbridge.bridge.BridgeHandlerManager;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019/3/5.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYFlutterHandler implements MethodChannel.MethodCallHandler {
    private static final String TAG = "android_bridge";
    private FlutterBridgeContext mContext;
    private BridgeHandlerManager mHandlerManager;

    public CYFlutterHandler(FlutterBridgeContext context) {
        this.mContext = context;
        mHandlerManager = new BridgeHandlerManager();
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        Logger.d(TAG, methodCall.method + " " + String.valueOf(methodCall.arguments));
        mHandlerManager.handle(mContext, methodCall, result);
    }
}
