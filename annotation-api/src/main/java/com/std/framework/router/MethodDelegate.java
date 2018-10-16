package com.std.framework.router;

import android.text.TextUtils;

import com.std.framework.router.exceptions.CYRouterException;
import com.std.framework.router.utils.ValueParser;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
    public static final String METHOD = "_method";
    public static final String ARGS = "_args";
    public static final String ARGS_TYPE = "_types";

    public static void invoke(String path, Map<String, Object> mapping, Object target, ParamsWrapper params) throws Exception {
        Method method = (Method) mapping.get(path + METHOD);
        String args = (String) mapping.get(path + ARGS);
        String types = (String) mapping.get(path + ARGS_TYPE);

        if (method == null) {
            throw new CYRouterException("path not found:" + path);
        }
        if (TextUtils.isEmpty(args)) {
            doReturn(method, params, method.invoke(target));
            return;
        }

        String[] argsName = args.split(",");
        String[] typesName = types.split(",");
        Object[] arr = new Object[argsName.length];
        for (int i = 0; i < argsName.length; i++) {
            arr[i] = ValueParser.parse(params.get(argsName[i]), typesName[i]);
        }
        doReturn(method, params, method.invoke(target, arr));
    }

    private static void doReturn(Method method, ParamsWrapper params, Object result) {
        Type type = method.getReturnType();
        if ("void".equals(type)) {
            params.getPromise().resolve(void.class);
        } else {
            params.getPromise().resolve(result);
        }
    }
}
