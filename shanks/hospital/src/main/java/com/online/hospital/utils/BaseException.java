package com.online.hospital.utils;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:14
 * Description:
 */
public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
