package com.std.base.mvvm.exception

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/9.
 */
sealed class Failure() {
    object netError : Failure()
    object serverError : Failure()

    /**
     * 扩展此方法实现具体业务错误定义
     */
    abstract class FeatureFailure : Failure()
}