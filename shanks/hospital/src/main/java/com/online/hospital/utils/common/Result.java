package com.online.hospital.utils.common;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:12
 * Description:
 */
public class Result<T> {


    //    error_code
    private Integer code;

    //    error_msg
    private String msg;

    //    content
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData(Object object) {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
