package com.online.hospital.utils.constants;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:13
 * Description:
 */
public class CommRespVO<T> {

    private int errorCode; //响应码
    private String errorMsg; //响应码对应的内容
    private String sign; //data字段的签名
    private T data;  //响应数据

    public CommRespVO() {
    }

    public CommRespVO(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommRespVO(Integer code, String msg, T data) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.data = data;
    }

    public CommRespVO(Integer code, String msg, String sign, T data) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.sign = sign;
        this.data = data;
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
        return "CommRespVO{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", sign='" + sign + '\'' +
                ", data=" + data +
                '}';
    }
}
