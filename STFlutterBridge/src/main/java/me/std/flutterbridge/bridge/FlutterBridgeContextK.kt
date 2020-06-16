package me.std.flutterbridge.bridge

import android.content.Context
import me.std.flutterbridge.bridge.handlers.CallBack

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/10.
 */
class FlutterBridgeContextK(context: Context, invoker: FlutterInvoker) {
    var context = context
    var invoker = invoker
    var pageInitArgs: Any? = null
    lateinit var bridgeView: IBridgeView
    var callBack: CallBack? = null
}