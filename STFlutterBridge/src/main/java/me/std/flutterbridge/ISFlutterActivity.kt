package me.std.flutterbridge

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.JSONMethodCodec
import io.flutter.plugin.common.MethodChannel
import me.std.flutterbridge.bridge.FlutterBridgeContextK
import me.std.flutterbridge.bridge.FlutterInvoker
import me.std.flutterbridge.bridge.IBridgeView
import me.std.flutterbridge.bridge.specs.FlutterPageParameter
import org.json.JSONObject

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/4.
 */
class ISFlutterActivity : FlutterActivity(), IBridgeView {
    private lateinit var flutterBridgeContext: FlutterBridgeContextK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromIntent()
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        flutterBridgeContext = ISFlutterChannel(this, MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL, JSONMethodCodec.INSTANCE))
                .apply {
                    bridgeContext.bridgeView = this@ISFlutterActivity
                }.bridgeContext
    }

    companion object {
        fun withCachedEngine(cachedEngineId: String): CachedEngineIntentBuilder? {
            return ISCachedEngineIntentBuilder(ISFlutterActivity::class.java, cachedEngineId).apply {
                destroyEngineWithActivity(false)
            }
        }

        fun withNewEngine(): NewEngineIntentBuilder {
            return ISNewEngineIntentBuilder(ISFlutterActivity::class.java)
        }
    }

    private fun fromIntent() {
        var parameter: FlutterPageParameter? = intent.getSerializableExtra(FlutterPageParameter.ARG_PARAMETER)?.let {
            it as FlutterPageParameter
        }
        flutterBridgeContext.pageInitArgs = parameter?.parameters
        update(parameter)
    }

    fun pushRouter(flutterPageParameter: FlutterPageParameter) {
        flutterEngine?.navigationChannel?.pushRoute(flutterPageParameter.route)
        update(flutterPageParameter)
    }

    fun pop(result: JSONObject?) {
        flutterEngine?.navigationChannel?.popRoute()
        flutterBridgeContext.callBack?.onResult(result, null)
    }

    override fun onBackPressed() {
        flutterBridgeContext.invoker.onBack { completed ->
            if (!completed) {
                super.onBackPressed();
            }
        }
    }

    override fun update(parameter: FlutterPageParameter?) {
        if (parameter == null) return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var result: JSONObject = JSONObject().apply {
            put("resultCode", resultCode)
            put("data", data?.getStringExtra("data"))
        }
        flutterBridgeContext.callBack?.onResult(result, null)
    }
}

class ISNewEngineIntentBuilder(activityClass: Class<out FlutterActivity>) :
        FlutterActivity.NewEngineIntentBuilder(activityClass)

class ISCachedEngineIntentBuilder(activityClass: Class<out FlutterActivity>, engineId: String) :
        FlutterActivity.CachedEngineIntentBuilder(activityClass, engineId)