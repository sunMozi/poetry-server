package com.poetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserLoginRequestDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Schema(description = "用户账号", example = "admin123")
  @NotBlank(message = "账号不能为空")
  @Size(max = 50, message = "账号长度不能超过50个字符")
  private String account;

  @Schema(description = "用户密码", example = "P@ssw0rd!")
  @NotBlank(message = "密码不能为空")
  @Size(max = 100, message = "密码长度不能超过100个字符")
  private String password;

  @Schema(description = "是否管理员，默认 false", example = "false")
  @Builder.Default
  private boolean isAdmin = false;

}