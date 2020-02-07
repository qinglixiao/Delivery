package me.std.flutterbridge.bridge.handlers;

import android.text.TextUtils;
import android.widget.Toast;

import com.std.network.request.BaseRequest;
import com.std.network.request.NetCallBack;
import com.std.network.request.Result;
import com.std.network.request.STRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import me.std.common.utils.Logger;
import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

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

        final STRequest.Builder request = new STRequest.Builder(url)
                .method(BaseRequest.STMethod.valueOf(method));
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
                Logger.e("BridgeHttpHandler", e.getMessage());
            }
        }

        request.build().request(new NetCallBack<String>() {

            @Override
            public void onResult(Result<String> result, Error error) {
                try {
                    if (error == null) {
                        String s = result.getData();
                        if (!TextUtils.isEmpty(result.getData())) {

                            if (s.startsWith("[") && s.endsWith("]")) {
                                JSONArray d = new JSONArray(s);
                                callback.onResult(d, null);
                            } else {
                                JSONObject o = new JSONObject(s);
                                callback.onResult(o, null);
                            }
                        }
                    } else {
                        Toast.makeText(context.context, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        callback.onResult(null, error);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context.context, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    callback.onResult(null, new Error(e));
                }
            }

        }, String.class);

        return false;
    }

    @Override
    protected void onDestroy() {

    }
}
