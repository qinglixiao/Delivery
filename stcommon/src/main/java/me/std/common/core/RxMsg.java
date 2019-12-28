package me.std.common.core;

/**
 * Description: 通过RxBus发送消息的消息体基类，所有消息必须从此类派生
 * Author: lixiao
 * Create on: 2018/9/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class RxMsg<T> {
    public T content;
}
