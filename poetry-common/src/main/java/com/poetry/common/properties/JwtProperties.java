package com.poetry.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 配置类，映射 application.yml 中的配置
 * @author moZiA
 */
@Data
@Component
@ConfigurationProperties(prefix = "poetry.jwt")
public class JwtProperties {

  /**
   * 签名密钥（32位以上安全字符串）
   */
  private String secret;

  /**
   * 默认过期时间，单位秒
   */
  private long expireSeconds;
}
