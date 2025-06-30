package com.poetry.service.impl.handler;

import com.poetry.common.enums.VerifyCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 */
@Slf4j
@Component
public class PhoneVerifyCodeHandler implements VerifyCodeHandler {

  @Override
  public boolean supports(VerifyCodeContext context) {
    return context.getType() == VerifyCodeType.PHONE;
  }

  @Override
  public void handle(VerifyCodeContext context) {
    log.info("处理手机验证码发送: recipient={}, code={}",
             context.getRecipient(),
             context.getCode());
    // 模拟短信发送
    // smsService.send(context.getRecipient(), context.getCode());
    // 放入缓存
  }
}
