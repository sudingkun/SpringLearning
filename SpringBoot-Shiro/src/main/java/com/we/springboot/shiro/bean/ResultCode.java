package com.we.springboot.shiro.bean;

import lombok.Getter;

/**
 * @Author admin
 * @create 2019/8/7 11:15
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    ACCOUNT_UNKNOWN(500, "账户不存在"),
    ACCOUNT_ERROR(500,"密码错误"),
    CODE_ERROR(400,"验证码错误"),
    SYSTEM_ERROR(500, "系统错误"),
    PARAM_ERROR(400, "参数错误"),
    PARAM_REPEAT(400, "参数已存在"),
    PERMISSION_ERROR(403, "没有操作权限"),
    OTHER(-100, "其他错误");


    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
