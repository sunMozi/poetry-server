package com.poetry.enums;

import lombok.Getter;

/**
 * @author moZiA
 */

@Getter
public enum VerifyCodeType {
  PHONE(1), EMAIL(2),
  ;

  private final int code;

  VerifyCodeType(int code) {
    this.code = code;
  }

  public static VerifyCodeType fromCode(int code) {
    for (VerifyCodeType type : values()) {
      if (type.code == code) {
        return type;
      }
    }
    throw new IllegalArgumentException("不支持的验证码类型: " + code);
  }
}
