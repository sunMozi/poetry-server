package com.poetry.controller.v1;

import com.poetry.WebInfoControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.service.WebInfoService;
import com.poetry.vo.WebInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网站信息表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */


@RestController
@RequiredArgsConstructor
public class WebInfoController implements WebInfoControllerApi {

  private final WebInfoService webInfoService;


  @Override
  public ResponseResult<WebInfoVO> getWebInfo() {
    return ResponseResult.ok(webInfoService.getWebInfo());
  }
}