package me.std.flutterbridge.bridge.handlers

import me.std.common.core.DataConvert
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.BridgeHandlerManagerK.Companion.getInstance
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import me.std.flutterbridge.bridge.specs.BridgeCallbackSpec
import me.std.flutterbridge.bridge.specs.BridgeParameterResolver
import me.std.flutterbridge.bridge.specs.BridgeResult
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/16.
 */
class BridgeCallNativeHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_CALL.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        val resolver = BridgeParameterResolver(arguments)

        val callSpec = DataConvert.fromJson(arguments.toString(), BridgeCallbackSpec::class.java)

        val handler = getInstance().getHandler(callSpec.method)

        if (handler == null) {
            callback!!.onResult(null, BridgeResult.notSupportedMethod(callSpec.method))
            return false
        }

        handler.handle(context, if (callSpec.parameters != null) JSONObject(callSpec.parameters as Map<*, *>) else JSONObject(), callback)

        return true
    }

    override fun onDestroy() {

    }
}