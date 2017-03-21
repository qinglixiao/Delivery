package com.std.framework.basic;

import android.content.Context;

/**
 * Created by gfy on 2016/5/6.
 */
public interface IBaseView<T> {
    void setPresenter(T presenter);

    Context getContext();
}
