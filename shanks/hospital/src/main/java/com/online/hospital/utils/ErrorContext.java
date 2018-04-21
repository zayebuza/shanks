package com.online.hospital.utils;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:16
 * Description:
 */
public class ErrorContext {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();

    private String message;
    private Throwable cause;

    private ErrorContext() {
    }

    public static ErrorContext instance() {
        ErrorContext context = LOCAL.get();
        if (context == null) {
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }

    public ErrorContext message(String message) {
        this.message = message;
        return this;
    }

    public ErrorContext cause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();

        // message
        if (this.message != null) {
            description.append(this.message).append(" ");
        }

        // cause
        if (cause != null) {
            description.append(cause.toString());
        }

        return description.toString();
    }

}
