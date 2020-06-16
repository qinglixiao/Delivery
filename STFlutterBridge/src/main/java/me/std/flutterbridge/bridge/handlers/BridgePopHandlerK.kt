package me.std.flutterbridge.bridge.handlers

import me.std.flutterbridge.ISFlutterActivity
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/15.
 */
class BridgePopHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_GO_BACK.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        if (context?.context is ISFlutterActivity) {
            var activity: ISFlutterActivity = context.context as ISFlutterActivity
            activity.pop(arguments)
        }
        callback?.onResult(null, null)
        return true
    }

    override fun onDestroy() {

    }
}