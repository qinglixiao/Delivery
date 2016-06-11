package com.std.framework.net.basic;

/**
 * Created by gfy on 2016/4/10.
 */
public class NetBean<T> {
    public String code;
    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
