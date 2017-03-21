package com.std.framework.basic;

/**
 * Created by gfy on 2016/5/6.
 */
public interface IBasePresenter<T> {
    void init(T data);
    void destroy();
}
