package com.research.manager.ldm.service.impl;

import com.research.manager.ldm.entity.User;
import com.research.manager.ldm.mapper.UserMapper;
import com.research.manager.ldm.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
