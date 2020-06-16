package me.std.flutterbridge.bridge

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/10.
 */
abstract class FlutterBridgeObjectK {
    fun destroy() {
        onDestroy()
    }

    abstract fun onDestroy();
}