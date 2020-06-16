package me.std.flutterbridge

import android.content.Context
import io.flutter.plugin.common.MethodChannel
import me.std.common.utils.Logger
import me.std.flutterbridge.bridge.FlutterInvoker
import me.std.flutterbridge.bridge.FlutterBridgeContext
import me.std.flutterbridge.bridge.FlutterBridgeContextK

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/6.
 */
class ISFlutterChannel {
    var methodChannel: MethodChannel
    var context: Context
    var bridgeContext: FlutterBridgeContextK
    var flutterHandler: ISFlutterHandler

    constructor(context: Context, methodChannel: MethodChannel) {
        this.context = context
        this.methodChannel = methodChannel
        bridgeContext = FlutterBridgeContextK(context, FlutterInvoker(methodChannel))
        flutterHandler = ISFlutterHandler(bridgeContext)
        methodChannel.setMethodCallHandler(flutterHandler)
    }

    fun invoke(method: String, parameters: Any, callback: FlutterInvoker.Callback) {
        Logger.d("call flutter method:${method},parameter:${parameters}")
        bridgeContext.invoker?.invoke(method, parameters, callback)
    }

}