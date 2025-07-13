package com.poetry.service.impl;


import com.poetry.common.utils.RedisKeyUtil;
import com.poetry.common.utils.RedisUtil;
import com.poetry.common.utils.VerifyCodeUtil;
import com.poetry.enums.VerifyCodeType;
import com.poetry.service.VerifyCodeService;
import com.poetry.service.impl.handler.VerifyCodeContext;
import com.poetry.service.impl.processor.VerifyCodeProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/30 11:07
 * @description
 */
@Service
@RequiredArgsConstructor
public class VerifyCodeServiceImpl implements VerifyCodeService {

  private final VerifyCodeProcessor verifyCodeProcessor;

  private final RedisUtil redisUtil;

  @Override
  public void sendVerifyCode(String email) {
    VerifyCodeType verifyCodeType = VerifyCodeType.fromCode(2);
    String code = VerifyCodeUtil.generateNumericCode(6);
    VerifyCodeContext context = VerifyCodeContext.builder()
                                                 .type(verifyCodeType)
                                                 .code(code)
                                                 .recipient(email)
                                                 .build();
    verifyCodeProcessor.process(context);
    redisUtil.set(RedisKeyUtil.verifyCodeKey(String.valueOf(verifyCodeType.getCode()), email),
                  code,
                  60);

  }
}