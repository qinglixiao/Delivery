package me.std.flutterbridge.bridge.specs;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import me.std.common.core.DataConvert;
import me.std.flutterbridge.bridge.handlers.CallBack;

public class FlutterPageParameter implements Serializable {
    public static String ARG_ROUTE = "route";
    public static String ARG_TITLE = "title";
    public static String ARG_SHOW_NAVIGATION_BAR = "show_navigation_bar";

    public static FlutterPageParameter fromJson(JSONObject json) {
        if (json == null) return null;
        FlutterPageParameter p = DataConvert.fromJson(json.toString(), FlutterPageParameter.class);
        return p;
    }

    /*
     * 传给flutter页面
     */
    public static String ARG_PARAMETER = "init_args";

    public FlutterPageParameter(String route, String title) {
        this.route = route;
        this.title = title;
        showNavigationBar = true;
    }

    public void fromIntent(Intent intent) {
        if (intent == null) return;

        title = intent.getStringExtra(ARG_TITLE);
        route = intent.getStringExtra(ARG_ROUTE);

        showNavigationBar = intent.getBooleanExtra(ARG_SHOW_NAVIGATION_BAR, true);
    }

    public void fromBundle(Bundle bundle) {
        if (bundle == null) return;

        title = bundle.getString(ARG_TITLE);
        route = bundle.getString(ARG_ROUTE);

        showNavigationBar = bundle.getBoolean(ARG_SHOW_NAVIGATION_BAR, false);
    }

    private String route;
    public String title;

    // default true
    public boolean showNavigationBar;

    public boolean replaceTop;

    public Map<String, Object> parameters;

    public BridgeCallbackSpec callbck;

    public List<BridgeActionButtonSpec> rightBarButtons;

    public String getRoute() {
        return route == null ? "default" : route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
