package com.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 *
 * @author Zyan
 * @since 2025-07-02 11:47:22
 */
@Data
@TableName("user")
public class User implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   *  主键ID
   */
  private Long id;

  /**
   *  用户名（唯一，用于登录）
   */
  private String username;

  /**
   *  用户昵称（展示名称，可重复）
   */
  private String nickname;

  /**
   *  密码（加密存储）
   */
  private String password;

  /**
   *  手机号
   */
  private String phoneNumber;

  /**
   *  邮箱
   */
  private String email;

  /**
   *  用户状态[0:禁用,1:启用]
   */
  private Integer userStatus;

  /**
   *  性别[0:保密,1:男,2:女]
   */
  private Integer gender;

  /**
   *  第三方OPENID
   */
  private String openId;

  /**
   *  头像URL
   */
  private String avatar;

  /**
   *  赞赏累计金额
   */
  private Float admireAmount;

  /**
   *  简介
   */
  private String introduction;

  /**
   *  用户类型[0:超级管理员,1:管理员,2:普通用户]
   */
  private Integer userType;

  /**
   *  创建时间
   */
  private Date createTime;

  /**
   *  创建人
   */
  private String createBy;

  /**
   *  更新时间
   */
  private Date updateTime;

  /**
   *  最后修改人
   */
  private String updateBy;

  /**
   *  是否已删除[0:未删除,1:已删除]
   */
  private Integer deleted;

}