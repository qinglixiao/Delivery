package com.std.network.request;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-12-30.
 */
public class Result<T> {
    String message;
    T data;

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
