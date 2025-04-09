package com.research.manager.sysmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.research.manager.sysmanager.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
public interface UserService extends IService<User> {
    public User getUserById(String id);
}
