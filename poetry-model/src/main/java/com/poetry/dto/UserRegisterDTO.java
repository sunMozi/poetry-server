package com.poetry.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 用户注册请求 DTO
 * @author moZiA
 */
@Data
public class UserRegisterDTO {

  @NotBlank(message = "用户名不能为空")
  @Pattern(regexp = "^(?!\\d{11}$)(?!.*@).*", message = "用户名不能为11位纯数字或包含@")
  private String username;

  /**
   * 手机号（可选，但必须和 email 二选一，由业务层保证）
   */
  @Pattern(regexp = "^\\d{11}$", message = "手机号格式不正确")
  private String phoneNumber;

  /**
   * 邮箱（可选，但必须和 phoneNumber 二选一，由业务层保证）
   */
  @Email(message = "邮箱格式不正确")
  private String email;

  @NotBlank(message = "密码不能为空")
  private String password;

  @NotBlank(message = "验证码不能为空")
  private String code;
}
