package com.poetry.oss.context;

import com.poetry.oss.config.OssProperties;
import com.poetry.oss.strategy.FileUploadStrategy;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileUploadContext {

  private final OssProperties ossProperties;
  private final Map<String, FileUploadStrategy> strategyMap;

  public FileUploadStrategy getStrategy() {
    String strategyKey = ossProperties.getStrategy();
    FileUploadStrategy strategy = strategyMap.get(strategyKey);
    if (strategy == null) {
      throw new IllegalArgumentException("未找到指定的 OSS 策略: " + strategyKey);
    }
    return strategy;
  }
}
