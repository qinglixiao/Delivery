package me.std.flutterbridge.bridge.specs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import me.std.common.core.DataConvert;

/**
 * Created by Roger Huang on 2019/3/14.
 */

public class BridgeResult {
    public static JSONObject make(Object result, Error error) {
        JSONObject r = new JSONObject();
        try {
            r.put("success", error == null);
            if (result != null) {
                if (result instanceof String) {
                    r.put("data", result);
                } else if (result instanceof JSONObject) {
                    r.put("data", result);
                } else if (result instanceof JSONArray) {
                    r.put("data", result);
                }else  if(result instanceof Map){
                    r.put("data",new JSONObject((Map)result));
                }
                else {
                    JSONObject o = new JSONObject(DataConvert.toJsonString(result));
                    r.put("data", o);
//                    throw new Exception("unsupported data type");
                }
            }
            if (error != null) {
                r.put("error", error.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static Error argumentRequired() {
        return new Error("argument required");
    }

    public static Error invalidArguments(Object arguments) {
        return new Error(String.format("wrong arguments: %s", arguments.toString()));
    }

    public static Error notSupportedMethod(String method) {
        return new Error(String.format("`%s` not supported", method));
    }

    public static Error methodFailed(String method, String msg) {
        return new Error(String.format("`%s` failed: `%s`", method, msg));
    }
}
