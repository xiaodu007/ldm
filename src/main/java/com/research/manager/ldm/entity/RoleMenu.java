package com.research.manager.ldm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 角色菜单绑定表
 * </p>
 *
 * @author weihao
 * @since 2025-04-02
 */
@Getter
@Setter
@ToString
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单ID
     */
    @TableId(value = "ROLE_MENU_ID", type = IdType.AUTO)
    private Integer roleMenuId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;
}
