package com.research.manager.ldm.exception;

import lombok.Getter;

@Getter
public enum BusinessCode implements StatusCode{
    BUSINESS_ERROR(9999,"业务异常"),
    SUCCESS(00000, "业务成功");

    private int code;
    private String msg;

    BusinessCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
