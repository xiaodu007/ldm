package com.research.manager.ldm.mapper;

import com.research.manager.ldm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

