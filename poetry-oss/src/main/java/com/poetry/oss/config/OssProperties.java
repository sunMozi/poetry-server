package com.poetry.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

  private String strategy;

  private Local local = new Local();
  private Aliyun aliyun = new Aliyun();
  private Minio minio = new Minio();

  @Data
  public static class Local {

    private String basePath;
    private String accessUrl;
  }

  @Data
  public static class Aliyun {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
  }

  @Data
  public static class Minio {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
  }
}
