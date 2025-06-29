package com.poetry.common.exception;

/**
 * 异常抛出工具类
 * 用于统一抛出业务异常
 */
public final class Exceptions {

  private Exceptions() {
    // 工具类不允许实例化
  }

  /**
   * 根据 Code 对象抛出异常
   *
   * @param code Code 枚举或实现
   */
  public static void cast(Code code) {
    throw new StockException(code);
  }

  /**
   * 根据自定义 code 和 msg 抛出异常
   *
   * @param code 数字 code
   * @param msg  异常信息
   */
  public static void cast(int code, String msg) {
    throw new StockException(code, msg);
  }

  /**
   * 根据 Code 对象和根因抛出异常
   *
   * @param code  Code 枚举或实现
   * @param cause 异常根因
   */
  public static void cast(Code code, Throwable cause) {
    throw new StockException(code, cause);
  }

  /**
   * 根据自定义 code、msg 和根因抛出异常
   *
   * @param code  数字 code
   * @param msg   异常信息
   * @param cause 异常根因
   */
  public static void cast(int code, String msg, Throwable cause) {
    throw new StockException(code, msg, cause);
  }
}
