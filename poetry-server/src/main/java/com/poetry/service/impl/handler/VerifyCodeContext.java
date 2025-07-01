package com.poetry.service.impl.handler;


import com.poetry.enums.VerifyCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moZiA
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeContext {


  /**
   * 验证码类型（手机号、邮箱等）
   */
  private VerifyCodeType type;

  /**
   * 验证码值（纯数字、字母或混合）
   */
  private String code;

  /**
   * 接收目标（手机号、邮箱等）
   */
  private String recipient;

  /**
   * 可选：发起用户ID（用于限流、记录日志等场景）
   */
  private Long userId;

}
