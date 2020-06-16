package me.std.flutterbridge.bridge.handlers

import me.std.flutterbridge.bridge.FlutterBridgeContextK
import me.std.flutterbridge.bridge.FlutterBridgeObjectK
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/10.
 */

interface CallBack {
    fun onResult(data: Any?, error: Error?)
}

abstract class BridgeHandlerK : FlutterBridgeObjectK() {
    abstract fun name(): String
    abstract fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean
}