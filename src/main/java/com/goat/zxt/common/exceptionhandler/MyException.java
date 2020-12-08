package com.goat.zxt.common.exceptionhandler;


public class MyException extends  RuntimeException {

    // 状态码
    private Integer code;

    // 异常信息
    private String  msg;

    public MyException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
