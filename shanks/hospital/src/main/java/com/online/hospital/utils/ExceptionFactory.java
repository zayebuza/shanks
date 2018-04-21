package com.online.hospital.utils;

import com.google.common.base.Joiner;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:17
 * Description:
 */
public class ExceptionFactory {

    private ExceptionFactory() {

    }

    public static RuntimeException createException(Exception e, String... message) {
        return new BaseException(ErrorContext.instance().message(Joiner.on(" ").join(message)).cause(e).toString(), e);
    }

    public static RuntimeException createException(String... message) {
        return new BaseException(ErrorContext.instance().message(Joiner.on(" ").join(message)).toString());
    }

    public static RuntimeException createException(Exception e) {
        return new BaseException(ErrorContext.instance().message("git exceptions").cause(e).toString(), e);
    }

    public static RuntimeException createException() {
        return new BaseException(ErrorContext.instance().message("got exceptions").toString());
    }

}
