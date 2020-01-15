package me.std.flutterbridge;

import org.json.JSONObject;

/**
 * Created by Roger Huang on 2019/1/21.
 */

public interface CYBridge {
    interface Callback {
        void onResult(JSONObject result, Error error);
    }

    boolean call(String method, JSONObject parameters, Callback callback);
}
