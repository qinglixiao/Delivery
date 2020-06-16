package me.std.flutterbridge.bridge.specs;

import java.io.Serializable;

public class BridgeCallbackSpec implements Serializable {
    public String method;
    public String source;
    public Object parameters;
}
