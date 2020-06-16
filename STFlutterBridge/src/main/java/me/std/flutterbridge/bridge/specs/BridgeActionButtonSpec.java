package me.std.flutterbridge.bridge.specs;

import org.json.JSONObject;

import java.io.Serializable;

/*
 * BridgeActionButtonSpec
 * flutter页面依此设置ActionBar右边的按钮
 */
public class BridgeActionButtonSpec implements Serializable {
    public String type; // 预设的button

    // 自定义button样式
    public String title;
    public String icon;

    // 自定义callback
    public BridgeCallbackSpec callback;

    // 传下来的data，主要是给预设button用
    public JSONObject data;
}
