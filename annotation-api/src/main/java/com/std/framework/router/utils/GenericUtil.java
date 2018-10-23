package com.std.framework.router.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/23.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class GenericUtil {

    /**
     * Try to get generic of object.
     *
     * @param t   any object.
     * @param <T> input type
     * @return generic type
     */
    public static <T> String tryGetGeneric(T t) {
        ParameterizedType pt = null;
        Type[] types = t.getClass().getGenericInterfaces();
        if (types.length != 0 && types[0] instanceof ParameterizedType) {
            pt = (ParameterizedType) types[0];
        } else {
            Type type = t.getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                pt = (ParameterizedType) type;
            }
        }

        if (pt == null) {
            return null;
        }

        Type[] actual = pt.getActualTypeArguments();
        String unsafe = actual[0].toString();
        String genericString;
        if (unsafe.contains(",")) {
            genericString = unsafe;
        } else {
            String[] elms = unsafe.split(" ");/*maybe get generic mark, like E,T*/
            genericString = elms.length <= 1 ? unsafe : elms[1];
        }
        return genericString.length() < 4 ? null : genericString;
    }
}
