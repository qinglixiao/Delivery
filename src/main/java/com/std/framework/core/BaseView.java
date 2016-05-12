package com.std.framework.core;

/**
 * Created by gfy on 2016/5/6.
 */
public interface BaseView<T extends Presenter> {
    void setPresenter(T presenter);
}
