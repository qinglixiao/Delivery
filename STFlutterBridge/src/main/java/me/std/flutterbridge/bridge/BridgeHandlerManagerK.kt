package me.std.flutterbridge.bridge

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import me.std.common.utils.Logger
import me.std.flutterbridge.bridge.handlers.*
import me.std.flutterbridge.bridge.specs.BridgeResult
import org.json.JSONObject
import java.lang.Exception

/**
 * Description:
 *
 * 1. 每个flutter页面，有一个 BridgeHandlerManager，注册页面特有的handler
 * 2. globalManager用于注册通用的（每个页面都有的）handler
 *
 * Author: lixiao
 * Create on: 2020/6/10.
 */
class BridgeHandlerManagerK {
    private val handlerMap: HashMap<String, BridgeHandlerK> = HashMap()

    companion object {
        private val instance: BridgeHandlerManagerK = BridgeHandlerManagerK()

        private val globalManager: BridgeHandlerManagerK = BridgeHandlerManagerK()

        fun getGlobalManager(): BridgeHandlerManagerK {
            return globalManager
        }

        fun getInstance(): BridgeHandlerManagerK {
            return instance
        }

        init {
            // 默认的handlers
            globalManager.register(BridgeOpenFlutterHandlerK())
            globalManager.register(BridgeGetInitArgsHandlerK())
            globalManager.register(BridgeCloseActivityHandlerK())
            globalManager.register(BridgePopHandlerK())
            globalManager.register(BridgeOpenActivityHandlerK())
            globalManager.register(BridgeOpenH5HandlerK())
            globalManager.register(BridgeCallNativeHandlerK())

//        globalManager.register(new BridgeEventHandler ())
//
//        globalManager.register(new BridgeEventSubscribeHandler ())
//
//        globalManager.register(new BridgeUpdatePageHandler ())
//
//        globalManager.register(new BridgeHttpHandler ())
        }
    }

    fun register(handler: BridgeHandlerK): Boolean {
        if (handlerMap.containsKey(handler.name())) {
            return false
        }
        handlerMap[handler.name()] = handler
        return true
    }

    fun unregister(handler: BridgeHandlerK) {
        var handler = handlerMap[handler.name()]!!
        if (handler != null) {
            handlerMap.remove(handler.name())
        }
    }

    fun getHandler(name: String): BridgeHandlerK? {
        return handlerMap[name]
    }

    fun getHandlerMap(): HashMap<String, BridgeHandlerK> {
        return handlerMap
    }

    fun handle(context: FlutterBridgeContextK, methodCall: MethodCall, result: MethodChannel.Result): Boolean {
        var handler = getHandler(methodCall.method)
        if (handler == null) {
            handler = getGlobalManager().getHandler(methodCall.method)
        }
        if (handler == null) {
            result.success(BridgeResult.make(null, BridgeResult.notSupportedMethod(methodCall.method)))
            return false
        }

        var arg: JSONObject? = methodCall.arguments?.let {
            methodCall.arguments as JSONObject
        }

        return try {
            handler.handle(context, arg, object : CallBack {
                override fun onResult(data: Any?, error: Error?) {
                    result.success(BridgeResult.make(data, error))
                }
            })
        } catch (e: Exception) {
            Logger.e(e.message)
            false
        }

    }

    fun onDestroy() {
        for (h in handlerMap.values) {
            h.destroy()
        }
        for (h in getGlobalManager().handlerMap.values) {
            h.destroy()
        }
    }
}