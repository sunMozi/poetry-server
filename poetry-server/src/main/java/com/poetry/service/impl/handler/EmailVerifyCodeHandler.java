package com.poetry.service.impl.handler;

import com.poetry.common.enums.VerifyCodeType;
import com.poetry.common.utils.MailUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailVerifyCodeHandler implements VerifyCodeHandler {

  private final MailUtil emailUtil;


  @Override
  public boolean supports(VerifyCodeContext context) {
    return context.getType() == VerifyCodeType.EMAIL;
  }

  @Override
  public void handle(VerifyCodeContext context) {
    log.info("处理邮箱验证码发送: recipient={}, code={}",
             context.getRecipient(),
             context.getCode());
    emailUtil.sendHtmlMailWithTemplate(context.getRecipient(),
                                       "验证码通知",
                                       "mail/verify-code",
                                       Map.of("code", context.getCode()));
  }
}
