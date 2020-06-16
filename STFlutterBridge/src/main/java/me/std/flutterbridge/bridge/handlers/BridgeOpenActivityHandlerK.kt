package me.std.flutterbridge.bridge.handlers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/15.
 */
class BridgeOpenActivityHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_OPEN.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        var router: String? = arguments?.optString("route")
        var data: String? = arguments?.optString("data")
        var requestCode: Int = arguments?.optInt("requestCode", 1)!!

        if (router == null) {
            throw IllegalArgumentException("router is null !!!")
        }

        if (context?.context is Activity) {
            (context.context as Activity).startActivityForResult(Intent(Intent.ACTION_VIEW, Uri.parse(router))?.apply {
                putExtra("data", data)
            }, requestCode)
        }

        context?.callBack = callback

        return true
    }

    override fun onDestroy() {

    }
}