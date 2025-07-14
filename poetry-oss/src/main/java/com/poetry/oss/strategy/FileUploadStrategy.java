package com.poetry.oss.strategy;


import java.io.InputStream;

/**
 * @author moZiA
 * @date 2025/7/13 15:00
 * @description
 */
public interface FileUploadStrategy {

  /**
   * 上传文件
   * @param inputStream 输入流
   * @param path 存储路径
   * @param filename 文件名
   * @return 文件访问 URL
   */
  String upload(InputStream inputStream, String path, String filename);

  /**
   * 删除文件
   * @param fullPath 存储全路径
   */
  void delete(String fullPath);
}