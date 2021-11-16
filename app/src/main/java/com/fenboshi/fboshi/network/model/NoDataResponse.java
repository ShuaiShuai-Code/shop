package com.fenboshi.fboshi.network.model;


public class NoDataResponse {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int retCode) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String errorMsg) {
        this.msg = msg;
    }
}
