package com.poetry.common.constants;


/**
 * @author moZiA
 * @date 2025/6/29 20:30
 * @description
 */
public class CommonConst {

  private CommonConst() {
    throw new RuntimeException("禁止实例化");
  }

  /**
   * 用户 token 过期时间，单位：秒
   * 这里示例为 3600 秒（即 1 小时）
   */
  public static final long USER_TOKEN_INTERVAL = 3600L;


}