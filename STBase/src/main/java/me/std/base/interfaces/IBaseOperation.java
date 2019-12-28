package me.std.base.interfaces;

/**
 * Description: 为Activity,Fragment基类定义一些公共的方法
 * Author: lixiao
 * Create on: 2018/12/19.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface IBaseOperation {
    /**
     * 显示加载提示框
     * 默认点击 返回/外侧 可取消
     */
    void showLoading();

    /**
     * 显示加载提示框
     *
     * @param cancelable 是否可取消
     */
    void showLoading(boolean cancelable);

    /**
     * 关闭加载框
     */
    void dismissLoading();

    /**
     * 显示空页面
     *
     * @return
     */
    void showEmptyView();

    /**
     * 错误信息展示
     */
    void showError();

    /**
     * 错误信息展示
     * @param error
     */
    void showError(String error);

    /**
     * 隐藏错误信息提示
     */
    void hideError();

    /**
     * 显示空页面
     *
     * @param hint 提示文案
     */
    void showEmptyView(String hint);

    /**
     * 隐藏空页面
     */
    void hideEmptyView();

    /**
     * 空页面点击后会调用此方法进行数据加载
     */
    void onRefresh();
}
