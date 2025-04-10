package com.research.manager.sysmanager.service.impl;


import com.research.manager.common.util.RedisUtil;
import com.research.manager.sysmanager.dto.LoginDto;
import com.research.manager.sysmanager.entity.User;
import com.research.manager.common.exception.BusinessCode;
import com.research.manager.common.exception.BusinessException;
import com.research.manager.sysmanager.security.SysSecurityProperties;
import com.research.manager.sysmanager.security.entity.SysUserDetail;
import com.research.manager.sysmanager.service.LoginService;
import com.research.manager.sysmanager.util.JwtUtil;
import com.research.manager.sysmanager.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysSecurityProperties properties;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public LoginVo userlogin(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new BusinessException(BusinessCode.LOGIN_FAILD.getMsg());
        }
        SysUserDetail sysUserDetail = (SysUserDetail)authenticate.getPrincipal();
        User user = sysUserDetail.getUser();
        Map<String, String> claims = new HashMap<>();
        claims.put("id", String.valueOf(user.getUserId()));
        String token = JwtUtil.createJWT(properties.getSecretKey(), properties.getTtlMillis(), claims);
        redisUtil.set("ldm_"+token, token, properties.getTtlMillis());
        LoginVo loginVo = LoginVo.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .realName(user.getRealName())
                .token(token)
                .build();
        return loginVo;
    }
}
