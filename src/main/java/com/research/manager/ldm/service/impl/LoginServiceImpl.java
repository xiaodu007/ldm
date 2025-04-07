package com.research.manager.ldm.service.impl;

import com.research.manager.ldm.dto.LoginDto;
import com.research.manager.ldm.entity.User;
import com.research.manager.ldm.exception.BusinessCode;
import com.research.manager.ldm.exception.BusinessException;
import com.research.manager.ldm.security.SysSecurityProperties;
import com.research.manager.ldm.security.entity.SysUserDetail;
import com.research.manager.ldm.service.LoginService;
import com.research.manager.ldm.util.JwtUtil;
import com.research.manager.ldm.util.RedisUtil;
import com.research.manager.ldm.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        redisUtil.set("ldm_", token, properties.getTtlMillis());
        LoginVo loginVo = LoginVo.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .realName(user.getRealName())
                .token(token)
                .build();
        return loginVo;
    }
}
