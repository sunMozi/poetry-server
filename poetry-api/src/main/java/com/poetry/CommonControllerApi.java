package com.poetry;


import com.poetry.common.response.ResponseResult;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author moZiA
 * @date 2025/7/13 14:26
 * @description
 */
@RestController
@RequestMapping("/common")
public interface CommonControllerApi {

  @PostMapping("/upload/image")
  ResponseResult<String> uploadImage(@RequestPart("file") @NotNull MultipartFile file)
      throws IOException;

}