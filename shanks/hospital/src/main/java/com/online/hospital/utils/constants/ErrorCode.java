package com.online.hospital.utils.constants;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:13
 * Description:
 */
public enum ErrorCode implements BaseEnum{
    SUCCESS(10000, "成功");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
