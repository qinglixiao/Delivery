package me.std.flutterbridge.bridge.handlers;

import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import me.chunyu.cycommon.utils.LogUtil;
import me.chunyu.cynetwork.CYRequest;
import me.chunyu.flutter.MethodSpec;
import me.chunyu.flutter.bridge.FlutterBridgeContext;
import me.chunyu.g7network.G7HttpMethod;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019/3/27.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class BridgeHttpHandler extends BridgeHandler {

    @Override
    public String name() {
        return MethodSpec.NAT_HTTP.name;
    }

    @Override
    public boolean handle(final FlutterBridgeContext context, JSONObject arguments, final Callback callback) {
        if (arguments == null || !arguments.has("url")) {
            throw new Error("flutter native http handler -> arguments error!");
        }
        final String url = arguments.optString("url");
        String method = arguments.has("method") ? arguments.optString("method") : "GET";

        final CYRequest request = new CYRequest(url);
        request.setMethod(G7HttpMethod.valueOf(method));
        if (arguments.has("parameters")) {
            try {
                JSONObject obj = arguments.getJSONObject("parameters");
                Iterator<String> keys = obj.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        String key = keys.next();
                        request.addParameter(key, String.valueOf(obj.opt(key)));
                    }
                }
            } catch (JSONException e) {
                LogUtil.e("BridgeHttpHandler", e.getMessage());
            }
        }

        request.request(new CYRequest.CYRequestCallback<String>() {
            @Override
            public void onResult(String result, Error error) {
                try {
                    if (error == null) {
                        if (!TextUtils.isEmpty(result)) {
                            if (result.startsWith("[") && result.endsWith("]")) {
                                JSONArray d = new JSONArray(result);
                                callback.onResult(d, null);
                            } else {
                                JSONObject o = new JSONObject(result);
                                callback.onResult(o, null);
                            }
                        }
                    } else {
                        Toast.makeText(context.context,error.getMessage(),Toast.LENGTH_SHORT).show();
//                        callback.onResult(null, error);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    callback.onResult(null, new Error(e));
                }
            }
        });

        return false;
    }

    @Override
    protected void onDestroy() {

    }
}
