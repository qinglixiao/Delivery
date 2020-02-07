package me.std.flutterbridge.bridge.handlers;

import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Capture;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.std.common.utils.Logger;
import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-06-28.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class BridgeOpenH5Handler extends BridgeHandler {
    @Override
    public String name() {
        return MethodSpec.NAT_OPEN_H5.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        String title = arguments.optString("title");
        String url = arguments.optString("url");

        Map<String, Object> param = new HashMap<>();
        param.put("activity", context.context);
        param.put("url", url);
        param.put("title", title);
        CYRouter.build("chunyu://CommonWebProvider/openH5", param).done(
                new Capture() {
                    @Override
                    public void exception(Exception e) {
                        Logger.e("lx", e.getMessage());
                    }
                });

        return true;
    }

    @Override
    protected void onDestroy() {

    }
}
