package me.std.common.exception;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ChunyuException extends Exception {
    private static final long serialVersionUID = -6213149635297151442L;

    public ChunyuException() {

    }

    public ChunyuException(String message) {
        super(message);
    }

    public ChunyuException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ChunyuException(Throwable cause) {
        super(cause);
    }
}
