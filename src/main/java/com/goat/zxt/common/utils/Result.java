package com.goat.zxt.common.utils;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一返回结果的类
 */
public class Result<T> implements Serializable {

    // 是否成功
    private Boolean success;

    // 返回码
    private Integer code;

    // 返回消息
    private String msg;

    // 总数
    private Long count;

    // 返回数据
    private List<T> data = new ArrayList<>();

    private T dataObject ;

    // token数据
    private String jwt;

    /**
     * 把构造方法私有
     */
    private Result() {}

    /**
     * 成功静态方法
     */
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("成功");
        return r;
    }

    /**
     * 失败静态方法
     */
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("失败");
        return r;
    }

    public static Result judge(int n,String msg){
        return n > 0 ? ok().message(msg + "成功") : error().message(msg +"失败");
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(List<T> list){
        this.data.addAll(list);
        return this;
    }

    public Result dataObject(T t){
        this.dataObject = t;
        return this;
    }

    public Result count(Long count){
        this.count = count;
        return this;
    }

    public Result jwt(String jwt){
        this.jwt = jwt;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public T getDataObject() {
        return dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }
}

