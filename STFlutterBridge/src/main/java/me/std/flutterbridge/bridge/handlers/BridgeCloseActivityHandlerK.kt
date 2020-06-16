package me.std.flutterbridge.bridge.handlers

import android.app.Activity
import android.content.Intent
import me.std.flutterbridge.ISFlutterActivity
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/15.
 */
class BridgeCloseActivityHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_CLOSE.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        if (context?.context is ISFlutterActivity) {
            val activity: ISFlutterActivity = context.context as ISFlutterActivity

            var result: String? = arguments?.get("result")?.toString()
            if (result?.equals("ok")!!) {
                activity.setResult(Activity.RESULT_OK, Intent().putExtra("data", arguments?.get("data").toString()))
            } else {
                activity.setResult(Activity.RESULT_CANCELED)
            }

            activity.finish()
        }

        return true
    }

    override fun onDestroy() {

    }
}