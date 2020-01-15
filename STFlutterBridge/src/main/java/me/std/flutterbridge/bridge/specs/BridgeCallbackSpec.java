package me.std.flutterbridge.bridge.specs;

import java.io.Serializable;

/**
 * Created by Roger Huang on 2019/3/14.
 */

public class BridgeCallbackSpec implements Serializable {
    public String method;
    public String source;
    public Object parameters;
}
