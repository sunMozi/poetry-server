package com.poetry.common.exception;

import lombok.Getter;


/**
 * 项目自定义异常
 * 支持 Code 对象、自定义 code+msg、自定义 cause
 */
@Getter
public class StockException extends RuntimeException implements Code {

  /**
   * 响应码
   */
  private final int code;

  /**
   * 响应信息
   */
  private final String msg;

  /**
   * 构造：基于 Code 枚举或实现
   */
  public StockException(Code codeObj) {
    super(codeObj.getMsg());
    this.code = codeObj.getCode();
    this.msg = codeObj.getMsg();
  }

  /**
   * 构造：自定义 code + msg
   */
  public StockException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  /**
   * 构造：基于 Code 枚举 + 根因异常
   */
  public StockException(Code codeObj, Throwable cause) {
    super(codeObj.getMsg(), cause);
    this.code = codeObj.getCode();
    this.msg = codeObj.getMsg();
  }

  /**
   * 构造：自定义 code + msg + 根因异常
   */
  public StockException(int code, String msg, Throwable cause) {
    super(msg, cause);
    this.code = code;
    this.msg = msg;
  }
}
