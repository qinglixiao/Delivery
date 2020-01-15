package me.std.flutterbridge;

import android.content.Context;

import com.std.framework.annotation.RouterModule;
import com.std.framework.annotation.RouterPath;

import java.util.Map;

import me.std.flutterbridge.bridge.handlers.OpenFlutterHandler;
import me.std.flutterbridge.bridge.specs.FlutterPageParameter;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-07-04.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@RouterModule(schema = "chunyu", host = "FlutterBridgeProvider")
public class CYFlutterBridgeProvider {

    @RouterPath(value = "/openFlutter")
    public void openFlutter(Context activity, String router, String title, boolean showBar, Map params) {
        FlutterPageParameter fp = new FlutterPageParameter();
        fp.setRoute(router);
        fp.setTitle(title);
        fp.showNavigationBar = showBar;
        fp.parameters = params;

        if (params != null && params.containsKey("requestCode")) {
            OpenFlutterHandler.openFlutterPage(activity, fp, (Integer) params.get("requestCode"));
        } else {
            OpenFlutterHandler.openFlutterPage(activity, fp, null);
        }

    }
}
