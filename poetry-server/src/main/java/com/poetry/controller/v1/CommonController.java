package com.poetry.controller.v1;


import com.poetry.CommonControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.oss.context.FileUploadContext;
import com.poetry.oss.strategy.FileUploadStrategy;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author moZiA
 * @date 2025/7/13 14:29
 * @description
 */
@RestController
@RequiredArgsConstructor
public class CommonController implements CommonControllerApi {

  private final FileUploadContext fileUploadContext;

  @Override
  public ResponseResult<String> uploadImage(MultipartFile file) throws IOException {
    FileUploadStrategy strategy = fileUploadContext.getStrategy();
    String url = strategy.upload(file.getInputStream(), "image", file.getOriginalFilename());
    return ResponseResult.ok(url);
  }
}