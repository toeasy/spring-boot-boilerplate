package com.xn.mars.domain;


/**
 * 响应结果封装
 * author:Liang.qinjie
 * on 2017-02-19 10:16
 */
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    /**
     * 私有构造
     */
    private Result() {
    }

    public static <T> Result<T> newInstance() {
        return new Result<>();
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("result=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    public static <T> Result<T> get(boolean success, String message, T data) {
        Result<T> result = Result.newInstance();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);

        return result;
    }

    public static <T> Result<T> success(String message, T data) {

        if ("".equals(message) || null == message) {
            message = "操作成功!";
        }

        return get(true, message, data);
    }

    public static <T> Result<T> success(T data) {

        return success(null, data);
    }

    public static <T> Result<T> success(String message) {
        return success(message, null);
    }


    public static <T> Result<T> fail(String message, T data) {

        if ("".equals(message) || null == message) {
            message = "未知错误!";
        }

        return get(false, message, data);
    }


    public static <T> Result<T> fail(String message) {
        return fail(message, null);
    }

    public static <T> Result<T> fail(T data) {
        return fail(null, data);
    }


}
