package me.std.flutterbridge

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import me.std.common.utils.Logger
import me.std.flutterbridge.bridge.BridgeHandlerManagerK
import me.std.flutterbridge.bridge.FlutterBridgeContextK

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/6.
 */
class ISFlutterHandler : MethodChannel.MethodCallHandler {
    val context: FlutterBridgeContextK
    val handlerManager: BridgeHandlerManagerK

    constructor(context: FlutterBridgeContextK) {
        this.context = context
        handlerManager = BridgeHandlerManagerK()
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Logger.d("call native method:${call.method},argument:${call.arguments}")
        handlerManager.handle(context, call, result)
    }
}