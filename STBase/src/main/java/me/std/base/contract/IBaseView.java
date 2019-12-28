package me.std.base.contract;

import android.content.Context;

/**
 * Description: 所有View层基类
 * Author: lixiao
 * Create on: 2018/8/7.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface IBaseView<T> {
    /**
     * 设置对应的presenter
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * 获取上下文
     * @return
     */
    Context getContext();
}
