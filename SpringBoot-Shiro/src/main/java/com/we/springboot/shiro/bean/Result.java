package com.we.springboot.shiro.bean;

import lombok.Data;

/**
 * 通用返回实体类
 *
 * @author: sudingkun
 * @date: 2019-08-06 16:50
 */
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    private Result(ResultCode enums) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    private Result(ResultCode enums, T data) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS, data);
    }

    public static Result error() {
        return new Result(ResultCode.SYSTEM_ERROR);
    }

    public static Result error(ResultCode enums) {
        return new Result(enums);
    }

}