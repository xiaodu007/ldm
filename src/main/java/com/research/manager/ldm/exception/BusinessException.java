package com.research.manager.ldm.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private int code;
    private String msg;

    public BusinessException(StatusCode statusCode, String message){
        super(message);
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BusinessException(String message){
        super(message);
        this.code = BusinessCode.BUSINESS_ERROR.getCode();
        this.msg = BusinessCode.BUSINESS_ERROR.getMsg();
    }
}
