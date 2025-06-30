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


  /**
   * 前缀
   */
  public static final String REDIS_KEY_PREFIX = "poetry";


  /**
   * 找回密码 / 注册验证码 缓存前缀
   * 用法: FORGET_PASSWORD + 手机号/邮箱 + "_1"/"_2"
   * _1 代表手机，_2 代表邮箱
   */
  public static final String FORGET_PASSWORD = "poetry:verifyCode";

  /**
   * 用户默认头像地址
   */
  public static final String DEFAULT_AVATAR = "https://cdn.poetry.com/avatar/default.png";


}