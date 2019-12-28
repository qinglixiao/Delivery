package me.std.common.exception;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ParseException extends ChunyuException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message,Throwable throwable){
        super(message,throwable);
    }
}
