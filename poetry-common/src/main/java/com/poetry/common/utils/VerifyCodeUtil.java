package com.poetry.common.utils;

import java.security.SecureRandom;

/**
 * 验证码生成工具类
 * 可生成数字验证码、字母验证码等。
 *
 * @author Zyan
 */
public final class VerifyCodeUtil {

  private static final SecureRandom RANDOM = new SecureRandom();

  private VerifyCodeUtil() {
    throw new UnsupportedOperationException("工具类禁止实例化");
  }

  /**
   * 生成纯数字验证码
   *
   * @param length 验证码长度
   * @return 验证码字符串
   */
  public static String generateNumericCode(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("验证码长度必须大于0");
    }

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(RANDOM.nextInt(10));
    }
    return sb.toString();
  }

  /**
   * 生成纯字母验证码（大写字母）
   *
   * @param length 验证码长度
   * @return 验证码字符串
   */
  public static String generateAlphaCode(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("验证码长度必须大于0");
    }

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = (char) ('A' + RANDOM.nextInt(26));
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * 生成混合验证码（数字 + 大写字母）
   *
   * @param length 验证码长度
   * @return 验证码字符串
   */
  public static String generateAlphaNumericCode(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("验证码长度必须大于0");
    }

    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
    }
    return sb.toString();
  }
}
