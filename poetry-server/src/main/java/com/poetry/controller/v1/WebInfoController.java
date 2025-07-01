package com.poetry.controller.v1;


import com.poetry.WebInfoControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.service.UserProfileService;
import com.poetry.vo.ProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/1 20:09
 * @description
 */
@RestController
@RequiredArgsConstructor
public class WebInfoController implements WebInfoControllerApi {

  private final UserProfileService userProfileService;


  @Override
  public ResponseResult<ProfileVO> profile() {
    return ResponseResult.ok(userProfileService.profile());
  }
}