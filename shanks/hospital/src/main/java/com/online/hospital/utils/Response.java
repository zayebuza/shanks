package com.online.hospital.utils;

import com.online.hospital.utils.constants.ErrorCode;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:18
 * Description:
 */
public class Response<T> {
    private int errorCode; //响应码
    private String errorMsg; //响应码对应的内容
    private String sign; //data字段的签名
    private T data;  //响应数据

    public Response() {
    }

    public Response(Integer code, String msg, T data) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.data = data;
    }

    public Response(Integer code, String msg, String sign, T data) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.sign = sign;
        this.data = data;
    }

    public static <D> Response<D> ofData(D data) {
        return new Response<D>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public static <D> Response<D> getErrorResponse(Integer code, String msg){
        Response<D> response = new Response<D>();
        response.setErrorCode(code);
        response.setErrorMsg(msg);

        return response;
    }


    /**
     * 设置成功响应的签名和数据
     * @param sign
     * @param data
     * @param <D>
     * @return
     */
    public static <D> Response<D> ofData(String sign, D data) {
        return new Response<D>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), sign, data);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", sign='" + sign + '\'' +
                ", data=" + data +
                '}';
    }
}
