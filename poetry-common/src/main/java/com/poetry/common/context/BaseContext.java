package com.poetry.common.context;

import com.poetry.common.core.pojo.TokenPayload;

/**
 * 基于 ThreadLocal 的用户上下文，用于保存 TokenPayload 信息
 * @author moZiA
 */
public class BaseContext {

  private static final ThreadLocal<TokenPayload> THREAD_LOCAL = new ThreadLocal<>();

  public static void set(TokenPayload payload) {
    THREAD_LOCAL.set(payload);
  }

  public static TokenPayload get() {
    return THREAD_LOCAL.get();
  }

  public static void remove() {
    THREAD_LOCAL.remove();
  }

  // 可选：提供快捷获取 userId 等字段的方法
  public static Long getUserId() {
    TokenPayload payload = THREAD_LOCAL.get();
    return payload != null ? payload.getUserId() : null;
  }

  public static Integer getUserType() {
    TokenPayload payload = THREAD_LOCAL.get();
    return payload != null ? payload.getUserType() : null;
  }

  public static String getSource() {
    TokenPayload payload = THREAD_LOCAL.get();
    return payload != null ? payload.getSource() : null;
  }

}
