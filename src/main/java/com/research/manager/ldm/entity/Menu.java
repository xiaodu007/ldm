package com.research.manager.ldm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 菜单信息表
 * </p>
 *
 * @author weihao
 * @since 2025-04-02
 */
@Getter
@Setter
@ToString
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 父级菜单ID
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 组件
     */
    private String component;

    /**
     * 是否可见
     */
    private String visible;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;
}
