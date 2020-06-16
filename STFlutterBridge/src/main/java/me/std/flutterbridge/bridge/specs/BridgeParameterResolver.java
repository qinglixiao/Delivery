package me.std.flutterbridge.bridge.specs;


public class BridgeParameterResolver {
    private Object mObjeject;

    /*
     * @param object 从flutter传过来的参数
     * 支持类型： 字符串，json对象, jsonarray, 数值, Object(视为异常数据), null
     */
    public BridgeParameterResolver(Object object) {
        mObjeject = object;
    }

    // TOOD: provide parser
}
