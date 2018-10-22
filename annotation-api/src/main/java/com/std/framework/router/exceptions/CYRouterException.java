package com.std.framework.router.exceptions;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/12.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYRouterException extends Exception {

    public CYRouterException(String message) {
        super(message);
    }

    public CYRouterException(Exception e) {
        super(e);
    }

    public CYRouterException(Throwable throwable) {
        super(throwable);
    }
}
