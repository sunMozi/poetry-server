package com.poetry.oss.strategy;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.poetry.oss.config.OssProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("aliyun")
@RequiredArgsConstructor
public class AliyunOssUploadStrategy implements FileUploadStrategy {

  private final OssProperties ossProperties;
  private OSS ossClient;

  @PostConstruct
  public void init() {
    var aliyun = ossProperties.getAliyun();
    ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(),
                                             aliyun.getAccessKey(),
                                             aliyun.getSecretKey());
  }

  @PreDestroy
  public void shutdown() {
    if (ossClient != null) {
      ossClient.shutdown();
    }
  }

  @Override
  public String upload(InputStream inputStream, String path, String filename) {
    var aliyun = ossProperties.getAliyun();
    String objectName = path + "/" + filename;
    ossClient.putObject(aliyun.getBucket(), objectName, inputStream);
    return String.format("https://%s.%s/%s", aliyun.getBucket(), aliyun.getEndpoint(), objectName);
  }

  @Override
  public void delete(String fullPath) {
    var aliyun = ossProperties.getAliyun();
    ossClient.deleteObject(aliyun.getBucket(), fullPath);
  }
}