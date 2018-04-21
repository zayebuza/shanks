package com.online.hospital.utils.common;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:11
 * Description:
 */
public enum ExceptionEnum {

    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
            ;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
