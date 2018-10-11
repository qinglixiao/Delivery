package com.std.framework.router;

import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/11.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class MethodDelegate {

    public static void invoke(String path, Map<String, MetaData> mapping, Object target, ParamsWrapper paramsWrapper) {
        MetaData metaData = mapping.get(path);
    }
}
