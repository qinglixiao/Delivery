package me.std.flutterbridge

import android.content.Context
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/6.
 */
object FlutterBridge {
    fun init(context: Context) {
        // Instantiate a FlutterEngine.
        FlutterEngine(context).apply {
//            navigationChannel.setInitialRoute("good_list_page")

            // Start executing Dart code to pre-warm the FlutterEngine.
            dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
            )

            // Cache the FlutterEngine to be used by FlutterActivity.
            FlutterEngineCache
                    .getInstance()
                    .put("flutter_engine", this)
        }
    }

    fun openFlutter() {

    }
}