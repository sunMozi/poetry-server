package com.poetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moZiA
 * @date 2025/06/29 14:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登录请求参数")
public class UserLoginDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Schema(description = "用户邮箱", example = "admin@example.com")
  @NotBlank(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  @Size(max = 64, message = "邮箱长度不能超过64个字符")
  private String account;

  @Schema(description = "用户密码", example = "P@ssw0rd!")
  @NotBlank(message = "密码不能为空")
  @Size(max = 100, message = "密码长度不能超过100个字符")
  private String password;
}