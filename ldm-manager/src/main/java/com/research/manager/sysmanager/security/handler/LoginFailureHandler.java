package com.research.manager.sysmanager.security.handler;

import com.alibaba.fastjson.JSON;

import com.research.manager.common.exception.BusinessCode;
import com.research.manager.common.vo.ResultVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String result = null;
        if (exception instanceof BadCredentialsException){
            result = JSON.toJSONString(new ResultVo(BusinessCode.USER_PASSWORD_ERROR));
        } else if (exception instanceof InternalAuthenticationServiceException) {
            result = JSON.toJSONString(new ResultVo(BusinessCode.USER_NAME_ISNULL));
        }else if (exception instanceof AccountExpiredException){
            result = JSON.toJSONString(new ResultVo(BusinessCode.ACCOUNT_EXPIRED));
        } else if (exception instanceof CredentialsExpiredException) {
            result = JSON.toJSONString(new ResultVo(BusinessCode.PASSWORD_EXPIRED));
        } else if (exception instanceof LockedException) {
            result = JSON.toJSONString(new ResultVo(BusinessCode.ACCOUNT_LOCK));
        } else if (exception instanceof DisabledException) {
            result = JSON.toJSONString(new ResultVo(BusinessCode.ACCOUNT_DISABLED));
        }else {
            result = JSON.toJSONString(new ResultVo(BusinessCode.BUSINESS_ERROR));
        }

        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
