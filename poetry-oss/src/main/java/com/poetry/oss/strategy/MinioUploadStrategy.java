package com.poetry.oss.strategy;

import com.poetry.oss.config.OssProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("minio")
@RequiredArgsConstructor
public class MinioUploadStrategy implements FileUploadStrategy {

  private final OssProperties ossProperties;
  private MinioClient minioClient;

  @PostConstruct
  public void init() {
    var minio = ossProperties.getMinio();
    minioClient = MinioClient.builder()
                             .endpoint(minio.getEndpoint())
                             .credentials(minio.getAccessKey(), minio.getSecretKey())
                             .build();
  }

  @Override
  public String upload(InputStream inputStream, String path, String filename) {
    var minio = ossProperties.getMinio();
    String objectName = path + "/" + filename;
    try {
      minioClient.putObject(PutObjectArgs.builder()
                                         .bucket(minio.getBucket())
                                         .object(objectName)
                                         .stream(inputStream, -1, 10485760)
                                         .contentType("application/octet-stream")
                                         .build());
      return String.format("%s/%s/%s", minio.getEndpoint(), minio.getBucket(), objectName);
    } catch (Exception e) {
      throw new RuntimeException("MinIO 文件上传失败", e);
    }
  }

  @Override
  public void delete(String fullPath) {
    var minio = ossProperties.getMinio();
    try {
      minioClient.removeObject(RemoveObjectArgs.builder()
                                               .bucket(minio.getBucket())
                                               .object(fullPath)
                                               .build());
    } catch (Exception e) {
      log.warn("MinIO 删除失败: {}", fullPath, e);
    }
  }
}
