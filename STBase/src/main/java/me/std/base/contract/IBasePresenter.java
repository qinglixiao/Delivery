package me.std.base.contract;

/**
 * Description: 所有Presenter基类
 * Author: lixiao
 * Create on: 2018/8/7.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface IBasePresenter<T> {
    /**
     * 清理资源
     */
    void onDestroy();
}
