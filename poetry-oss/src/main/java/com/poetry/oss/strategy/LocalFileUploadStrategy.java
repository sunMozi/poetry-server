package com.poetry.oss.strategy;

import com.poetry.oss.config.OssProperties;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("local")
@RequiredArgsConstructor
public class LocalFileUploadStrategy implements FileUploadStrategy {

  private final OssProperties ossProperties;

  @Override
  public String upload(InputStream inputStream, String path, String filename) {
    try {
      Path targetPath = Paths.get(ossProperties.getLocal().getBasePath(), path, filename);
      Files.createDirectories(targetPath.getParent());
      Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
      return String.format("%s/%s/%s", ossProperties.getLocal().getAccessUrl(), path, filename);
    } catch (Exception e) {
      throw new RuntimeException("本地文件上传失败", e);
    }
  }

  @Override
  public void delete(String fullPath) {
    try {
      Path filePath = Paths.get(ossProperties.getLocal().getBasePath(), fullPath);
      Files.deleteIfExists(filePath);
    } catch (Exception e) {
      log.warn("本地文件删除失败: {}", fullPath, e);
    }
  }
}