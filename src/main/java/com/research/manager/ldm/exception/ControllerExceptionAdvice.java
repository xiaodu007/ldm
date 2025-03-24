package com.research.manager.ldm.exception;

import com.research.manager.ldm.dto.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResultVo BusinessExceptionHandler(BusinessException e){
        log.error("发生业务异常{}", e.getMessage());
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo OtherExceptionHandler(Exception e){
        log.error("发生其他异常{}", e.getMessage());
        return new ResultVo(BusinessCode.BUSINESS_ERROR.getCode(), BusinessCode.BUSINESS_ERROR.getMsg(), e.getMessage());
    }
}
