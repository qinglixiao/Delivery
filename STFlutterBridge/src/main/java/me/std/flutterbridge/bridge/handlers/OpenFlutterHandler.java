package me.std.flutterbridge.bridge.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import org.json.JSONObject;

import me.std.flutterbridge.CYFlutterActivity;
import me.std.flutterbridge.MethodSpec;
import me.std.flutterbridge.bridge.FlutterBridgeContext;
import me.std.flutterbridge.bridge.specs.FlutterPageParameter;

/**
 * Created by Roger Huang on 2019/3/14.
 * 打开flutter页面
 */

public class OpenFlutterHandler extends BridgeHandler {

    @Override
    public String name() {
        return MethodSpec.FLT_OPEN_FLUTTER.name;
    }

    @Override
    public boolean handle(FlutterBridgeContext context, JSONObject arguments, Callback callback) {
        if (openFlutterPage(context.context, arguments)) {
            callback.onResult(null, null);
        } else {
            callback.onResult(null, new Error("invalid parameters"));
        }

        return true;
    }

    public static boolean openFlutterPage(Context context, FlutterPageParameter parameters, int... requestCode) {
        if (context == null || parameters == null) return false;

        Intent intent = new Intent(context, CYFlutterActivity.class);

        intent.setAction("android.intent.action.RUN");

        intent.putExtra(FlutterPageParameter.ARG_ROUTE, parameters.getRoute());

        if (parameters != null) {
            intent.putExtra(FlutterPageParameter.ARG_PARAMETER, parameters);
        }

        if (parameters.title != null) {
            intent.putExtra(FlutterPageParameter.ARG_TITLE, parameters.title);
        }

        intent.putExtra(FlutterPageParameter.ARG_SHOW_NAVIGATION_BAR, parameters.showNavigationBar);

        if (requestCode != null && requestCode.length > 0 && context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode[0]);
        } else {
            context.startActivity(intent);
        }
        if (parameters.replaceTop) {
            try {
                ((Activity) context).finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return true;
    }

    public static boolean openFlutterPage(Context context, JSONObject parameters) {
        if (parameters == null || context == null) {
            return false;
        }

        FlutterPageParameter p = FlutterPageParameter.fromJson(parameters);

        return openFlutterPage(context, p);
    }

    @Override
    protected void onDestroy() {

    }
}
