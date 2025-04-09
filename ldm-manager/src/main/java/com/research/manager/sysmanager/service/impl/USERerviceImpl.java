package com.research.manager.sysmanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.research.manager.sysmanager.entity.User;
import com.research.manager.sysmanager.mapper.UserMapper;
import com.research.manager.sysmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
@Service
public class USERerviceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }
}
