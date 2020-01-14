package com.std.network.request;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-12-30.
 */
public interface NetCallBack<T> {
    void onResult(Result<T> result, Error error);
}
