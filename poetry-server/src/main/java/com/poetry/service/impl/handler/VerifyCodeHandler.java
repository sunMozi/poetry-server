package com.poetry.service.impl.handler;

/**
 * @author moZiA
 */
public interface VerifyCodeHandler {

  /**
   * 判断是否支持当前处理
   */
  boolean supports(VerifyCodeContext context);

  /**
   * 处理逻辑
   */
  void handle(VerifyCodeContext context);
}
