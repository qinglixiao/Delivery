package me.std.flutterbridge.bridge.handlers

import android.app.Activity
import android.content.Context
import android.content.Intent
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import me.std.flutterbridge.ISFlutterActivity
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import me.std.flutterbridge.bridge.specs.FlutterPageParameter
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/10.
 */
class BridgeOpenFlutterHandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.FLT_OPEN_FLUTTER.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        context?.callBack = callback
        FlutterPageParameter.fromJson(arguments).apply {
            if (context?.context is ISFlutterActivity) {
                (context?.context as ISFlutterActivity).pushRouter(this)
            }
        }
        return true
    }

    companion object {
        fun openFlutterPage(context: Context?, parameters: FlutterPageParameter, vararg requestCode: Int): Boolean {
            if (context == null || parameters == null) {
                return false
            }

            var intent: Intent = ISFlutterActivity.withNewEngine().build(context)
                    .apply {
                        putExtra(FlutterPageParameter.ARG_ROUTE, parameters.route)
                        putExtra(FlutterPageParameter.ARG_PARAMETER, parameters)
                        putExtra(FlutterPageParameter.ARG_TITLE, parameters.title)
                        putExtra(FlutterPageParameter.ARG_SHOW_NAVIGATION_BAR, parameters.showNavigationBar)
                    }

            if (requestCode != null && requestCode.isNotEmpty() && context is Activity) {
                context.startActivityForResult(intent, requestCode[0])
            } else {
                context.startActivity(intent)
            }
            if (parameters.replaceTop) {
                try {
                    (context as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return true
        }
    }

    override fun onDestroy() {

    }
}