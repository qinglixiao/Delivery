package me.std.flutterbridge.bridge;

/**
 * Created by Roger Huang on 2019/3/16.
 */

public abstract class FlutterBridgeObject {
    public void destroy() {
        onDestroy();
    }
    protected abstract void onDestroy();
}
