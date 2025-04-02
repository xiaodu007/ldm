package com.research.manager.ldm.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.research.manager.ldm.entity.User;
import com.research.manager.ldm.exception.BusinessException;
import com.research.manager.ldm.mapper.RoleMapper;
import com.research.manager.ldm.mapper.UserMapper;
import com.research.manager.ldm.security.SysSecurityProperties;
import com.research.manager.ldm.security.entity.SysUserDetail;
import com.research.manager.ldm.security.handler.LoginFailureHandler;
import com.research.manager.ldm.util.JwtUtil;
import com.research.manager.ldm.util.RedisUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private SysSecurityProperties securityProperties;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String uri = request.getRequestURI();
            if (!uri.equals("/user/login")){
                this.validateToekn(request, response);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }

        filterChain.doFilter(request, response);
    }

    public void validateToekn(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(securityProperties.getTokenHeader());
        if (ObjectUtils.isEmpty(token)){
            token = request.getParameter(securityProperties.getTokenHeader());
        }
        if (ObjectUtils.isEmpty(token)){
            response.setStatus(401);
            throw new BusinessException("token为空");
        }
        String redisToken = redisUtil.get("ldm_" + token).toString();
        if (ObjectUtils.isEmpty(redisToken)){
            response.setStatus(401);
            throw new BusinessException("token已过期");
        }

        DecodedJWT decodedJWT = JwtUtil.parseJWT(securityProperties.getSecretKey(), token);
        String id = decodedJWT.getClaim("id").asString();
        User user = userMapper.selectById(id);
        List<String> permissions = roleMapper.findMenusByUserId(Integer.valueOf(id));
        SysUserDetail sysUserDetail = new SysUserDetail(user, permissions);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUserDetail, null, sysUserDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
