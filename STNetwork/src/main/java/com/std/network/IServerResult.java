package com.std.network;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838
 * Create on: 2019-12-28.
 */
public interface IServerResult {
    int getErrorCode();
    boolean getSuccess();
    String getErrorMessage();
}
