package me.std.flutterbridge.bridge.handlers

import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/11.
 */
class BridgeGetInitArgsHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_GET_INIT_ARGS.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        callback?.onResult(context?.pageInitArgs, null)
        return true
    }

    override fun onDestroy() {

    }
}