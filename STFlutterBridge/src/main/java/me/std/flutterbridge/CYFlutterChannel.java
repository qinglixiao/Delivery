package me.std.flutterbridge;

import android.content.Context;

import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;
import me.std.flutterbridge.bridge.BridgeInvoker;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

/**
 * Created by Roger Huang on 2019/1/19.
 */
public class CYFlutterChannel {
    public static final String CHANNEL = "flutter.chunyu.com.channel.method";

    private CYFlutterHandler flutterHandler;
    private MethodChannel methodChannel = null;
    private FlutterBridgeContext mBridgeContext;

    public CYFlutterChannel(Context context, FlutterView flutterView) {
        this(context, flutterView, CHANNEL);
    }

    public CYFlutterChannel(Context context, FlutterView flutterView,String channel) {
        methodChannel = new MethodChannel(flutterView, channel, JSONMethodCodec.INSTANCE);
        mBridgeContext = new FlutterBridgeContext(context, new BridgeInvoker(methodChannel));
        flutterHandler = new CYFlutterHandler(mBridgeContext);
        methodChannel.setMethodCallHandler(flutterHandler);
    }

    public FlutterBridgeContext getBridgeContext() {
        return mBridgeContext;
    }

    /**
     * 调用flutter
     *
     * @param method
     * @param parameters
     * @param callback
     */
    public void invoke(String method, Object parameters, BridgeInvoker.Callback callback) {
        mBridgeContext.invoker.invoke(method, parameters, callback);
    }

}
