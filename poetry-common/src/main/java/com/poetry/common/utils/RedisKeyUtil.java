package com.poetry.common.utils;

import com.poetry.common.constants.CommonConst;
import org.springframework.util.StringUtils;


/**
 * Redis Key 工具类，集中管理 key 构建逻辑
 * @author moZiA
 */
public class RedisKeyUtil {

  private static final String PREFIX = CommonConst.REDIS_KEY_PREFIX;

  private RedisKeyUtil() {
    throw new RuntimeException("禁止实例化");
  }

  public static String userTokenKey(Long userId) {
    return String.format("%s:user:token:%d:access", PREFIX, userId);
  }

  public static String adminTokenKey(Long userId) {
    return String.format("%s:admin:token:%d:access", PREFIX, userId);
  }

  /**
   * 构建验证码缓存 key，根据手机号或邮箱自动生成
   */
  public static String verifyCodeKey(com.poetry.dto.UserRegisterDTO dto) {
    if (StringUtils.hasText(dto.getPhoneNumber())) {
      return verifyCodeKey(dto.getPhoneNumber(), "1");
    } else if (StringUtils.hasText(dto.getEmail())) {
      return verifyCodeKey(dto.getEmail(), "2");
    }
    throw new IllegalArgumentException("手机号或邮箱至少填写一个");
  }

  /**
   * 构建验证码 key，支持 value 和自定义场景 code
   */
  public static String verifyCodeKey(String value, String sceneCode) {
    return String.format("%s:%s_%s", CommonConst.FORGET_PASSWORD, value, sceneCode);
  }
}
