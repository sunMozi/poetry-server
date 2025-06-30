package com.poetry.service.impl.processor;

import com.poetry.common.exception.Exceptions;
import com.poetry.service.impl.handler.VerifyCodeContext;
import com.poetry.service.impl.handler.VerifyCodeHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 */
@Component
@RequiredArgsConstructor
public class VerifyCodeProcessor {

  private final List<VerifyCodeHandler> handlers;


  public void process(VerifyCodeContext context) {
    for (VerifyCodeHandler handler : handlers) {
      if (handler.supports(context)) {
        handler.handle(context);
        return;
      }
    }
    Exceptions.cast(400, "不支持的验证码类型！");
  }
}
