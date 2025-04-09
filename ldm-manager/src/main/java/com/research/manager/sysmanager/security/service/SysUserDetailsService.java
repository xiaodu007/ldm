package com.research.manager.sysmanager.security.service;


import com.research.manager.sysmanager.entity.User;
import com.research.manager.sysmanager.mapper.UserMapper;
import com.research.manager.sysmanager.security.entity.SysUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SysUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new SysUserDetail(user);
    }
}
