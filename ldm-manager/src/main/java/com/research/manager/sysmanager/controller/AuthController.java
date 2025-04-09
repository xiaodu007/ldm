package com.research.manager.sysmanager.controller;

import com.research.manager.sysmanager.dto.LoginDto;
import com.research.manager.sysmanager.exception.BusinessCode;
import com.research.manager.sysmanager.exception.BusinessException;
import com.research.manager.sysmanager.security.SysSecurityProperties;
import com.research.manager.sysmanager.service.LoginService;
import com.research.manager.sysmanager.util.RedisUtil;
import com.research.manager.sysmanager.vo.LoginVo;
import com.research.manager.sysmanager.vo.ResultVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SysSecurityProperties securityProperties;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo login(@RequestBody LoginDto loginDto){
        LoginVo userlogin = loginService.userlogin(loginDto);
        return new ResultVo(BusinessCode.SUCCESS, userlogin);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public ResultVo loginOut(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(securityProperties.getTokenHeader());
        if (ObjectUtils.isEmpty(token)){
            token = request.getParameter(securityProperties.getTokenHeader());
        }
        if (ObjectUtils.isEmpty(token)){
            response.setStatus(401);
            throw new BusinessException("token为空");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            redisUtil.delete("ldm_"+token);
        }
        return new ResultVo(BusinessCode.SUCCESS);
    }
}
