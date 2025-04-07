package com.research.manager.ldm.exception;

import lombok.Getter;

@Getter
public enum BusinessCode implements StatusCode{
    BUSINESS_ERROR(9999,"业务异常"),
    SUCCESS(00000, "业务成功"),
    USER_NAME_ISNULL(500, "用户名为空"),
    ANONYMOUS_USER_NO_PERMISSION (500, "匿名用户无权访问"),
    PERMISSION_DENIED(500, "权限不足"),
    ACCOUNT_EXPIRED(500, "账户过期"),
    PASSWORD_EXPIRED(500, "密码过期"),
    ACCOUNT_LOCK(500, "账户被锁"),
    ACCOUNT_DISABLED(500, "账户失效"),
    USER_PASSWORD_ERROR(500,"用户名密码错误"),
    LOGIN_FAILD(500, "登录失败！");

    private int code;
    private String msg;

    BusinessCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
