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
 * 用户信息表
 * </p>
 *
 * @author weihao
 * @since 2025-03-24
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户登录账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 状态
     */
    private String status;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
