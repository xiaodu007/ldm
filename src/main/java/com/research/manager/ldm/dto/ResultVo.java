package com.research.manager.ldm.dto;

import com.research.manager.ldm.exception.BusinessCode;
import com.research.manager.ldm.exception.StatusCode;
import lombok.Data;

@Data
public class ResultVo {
    // 状态码
    private int code;
    //状态信息
    private String msg;
    //返回结果
    private Object data;

    public ResultVo(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(Object data){
        this.code = BusinessCode.SUCCESS.getCode();
        this.msg = BusinessCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultVo(StatusCode statusCode, Object data){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public ResultVo(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }

}
