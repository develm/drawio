package com.sql.er.common.utils;

/**
 * 通用result包装类
 *
 * @param <T>
 */
public class Result<T> {

    private static final int SUCCESS = 0;
    private static final int COMMON_FAIL = 911;
    public static final int UN_LOGIN = -1;
    public static final int UN_REGISTER = -2;
    public static final int PASSWORD_ERROR = -3;
    public static final int TOKEN_EXPIRE = -4;

    private int code = SUCCESS;

    private String msg;

    private T data;

    public static boolean isSuccess(Result result) {
        return SUCCESS == result.getCode();
    }

    public static <T> Result<T> createSuccess(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        return fail(COMMON_FAIL, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
