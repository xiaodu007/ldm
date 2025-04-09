package com.research.manager.sysmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.research.manager.sysmanager.entity.Role;

import java.util.List;


/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    public List<String> findMenusByUserId(Integer userId);

}

