package com.poetry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * 用户信息视图对象
 * 用于对外展示用户信息，屏蔽敏感字段
 *
 * @author Zyan
 * @created 2025-06-29
 */
@Data
@Builder
public class UserVO implements Serializable {

  @Schema(description = "访问令牌")
  private String accessToken;

  @Schema(description = "用户头像URL")
  private String avatar;

  @Schema(description = "用户编码")
  private String code;

  @Schema(description = "创建时间")
  private String createTime;

  @Schema(description = "电子邮箱")
  private String email;

  @Schema(description = "性别（0：女，1：男）")
  private Integer gender;

  @Schema(description = "用户ID")
  private Long id;

  @Schema(description = "个人简介")
  private String introduction;

  @Schema(description = "是否为老板")
  private Boolean isBoss;

  @Schema(description = "手机号码")
  private String phoneNumber;

  @Schema(description = "订阅状态")
  private String subscribe;

  @Schema(description = "更新人")
  private String updateBy;

  @Schema(description = "更新时间")
  private String updateTime;

  @Schema(description = "用户名")
  private String username;
}