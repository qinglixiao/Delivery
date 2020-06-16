package me.std.flutterbridge.bridge.handlers

import com.std.framework.router.CYRouter
import me.std.common.utils.Logger
import me.std.flutterbridge.MethodSpecK
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import org.json.JSONObject
import java.util.*

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/16.
 */
class BridgeOpenH5HandlerK : BridgeHandlerK() {
    override fun name(): String {
        return MethodSpecK.NAT_OPEN_H5.realName
    }

    override fun handle(context: FlutterBridgeContextK?, arguments: JSONObject?, callback: CallBack?): Boolean {
        val title = arguments?.optString("title")!!
        val url = arguments?.optString("url")!!

        val param: MutableMap<String, Any> = HashMap()
        param["activity"] = context!!.context
        param["url"] = url
        param["title"] = title
        CYRouter.build<Any>("chunyu://CommonWebProvider/openH5", param).done<Any> { e -> Logger.e("lx", e.message) }

        return true
    }

    override fun onDestroy() {

    }
}