package com.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 作者表，存储作者基础信息
 *
 * @author Zyan
 * @since 2025-07-02 15:37:30
 */
@Data
@TableName("author")
public class Author implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   *  作者ID，主键
   */
  private Long id;

  /**
   *  作者昵称
   */
  private String nickname;

  /**
   *  真实姓名，可选
   */
  private String realName;

  /**
   *  头像URL
   */
  private String avatar;

  /**
   *  作者简介
   */
  private String bio;

  /**
   *  联系邮箱
   */
  private String email;

  /**
   *  个人主页或博客URL
   */
  private String website;

  /**
   *  状态：1=启用，0=禁用
   */
  private Integer status;

  /**
   *  创建时间
   */
  private LocalDateTime createTime;

  /**
   *  更新时间
   */
  private LocalDateTime updateTime;

}