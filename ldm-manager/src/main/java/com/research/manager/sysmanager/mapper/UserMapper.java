package com.research.manager.sysmanager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.research.manager.sysmanager.entity.User;


/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
public interface UserMapper extends BaseMapper<User> {
    public User findByUsername(String username);
}

