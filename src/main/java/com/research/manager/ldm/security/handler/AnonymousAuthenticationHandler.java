package com.research.manager.ldm.security.handler;

import com.alibaba.fastjson.JSON;
import com.research.manager.ldm.dto.ResultVo;
import com.research.manager.ldm.exception.BusinessCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String result = null;
        ServletOutputStream outputStream = response.getOutputStream();
        if (authException instanceof BadCredentialsException){
            result = JSON.toJSONString(new ResultVo(BusinessCode.USER_PASSWORD_ERROR));
        } else if (authException instanceof InternalAuthenticationServiceException) {
            result = JSON.toJSONString(new ResultVo(BusinessCode.USER_NAME_ISNULL));
        }else {
            result = JSON.toJSONString(new ResultVo(BusinessCode.ANONYMOUS_USER_NO_PERMISSION));
        }

        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
