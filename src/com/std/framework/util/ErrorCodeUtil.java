
package com.std.framework.util;

import android.util.SparseArray;

/**
 * 服务端返回的错误码跟提示语的对应关系
 */
public class ErrorCodeUtil {

    private static final SparseArray<String> relation = new SparseArray<String>();

    static {
        /************* 用户 *****************/
        relation.put(1000, "login_001");

    }

    /**
     * 获取提示语
     *
     * @param errorCode 错误码
     * @return
     */
    public static String getString(int errorCode) {
        return ResourceUtil.getString(relation.get(errorCode));
    }

}
