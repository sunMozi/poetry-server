package com.poetry.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户信息表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("user")
public class User implements Serializable{

    @Serial
    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Long id;

    /**
     *  用户名
     */
    @NotBlank(message = "【用户名】不能为空")
    private String username;

    /**
     *  密码
     */
    @NotBlank(message = "【密码】不能为空")
    private String password;

    /**
     *  手机号
     */
    @NotBlank(message = "【手机号】不能为空")
    private String phoneNumber;

    /**
     *  用户邮箱
     */
    @NotBlank(message = "【用户邮箱】不能为空")
    private String email;

    /**
     *  是否启用[0:否，1:是]
     */
    @NotBlank(message = "【是否启用[0:否，1:是]】不能为空")
    private Integer userStatus;

    /**
     *  性别[1:男，2:女，0:保密]
     */
    @NotBlank(message = "【性别[1:男，2:女，0:保密]】不能为空")
    private Integer gender;

    /**
     *  OPENID
     */
    @NotBlank(message = "【OPENID】不能为空")
    private String openId;

    /**
     *  头像
     */
    @NotBlank(message = "【头像】不能为空")
    private String avatar;

    /**
     *  赞赏
     */
    @NotBlank(message = "【赞赏】不能为空")
    private String admire;

    /**
     *  订阅
     */
    @NotBlank(message = "【订阅】不能为空")
    private String subscribe;

    /**
     *  简介
     */
    @NotBlank(message = "【简介】不能为空")
    private String introduction;

    /**
     *  用户类型[0:ADMIN，1:管理员，2:普通用户]
     */
    @NotBlank(message = "【用户类型[0:ADMIN，1:管理员，2:普通用户]】不能为空")
    private Integer userType;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

    /**
     *  最终修改时间
     */
    @NotBlank(message = "【最终修改时间】不能为空")
    private Date updateTime;

    /**
     *  最终修改人
     */
    @NotBlank(message = "【最终修改人】不能为空")
    private String updateBy;

    /**
     *  是否启用[0:未删除，1:已删除]
     */
    @NotBlank(message = "【是否启用[0:未删除，1:已删除]】不能为空")
    private Integer deleted;

}