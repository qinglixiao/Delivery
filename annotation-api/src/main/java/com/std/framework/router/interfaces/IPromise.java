package com.std.framework.router.interfaces;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/15.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface IPromise<T> {
    void resolve(T data);

    void capture(Exception ex);
}
