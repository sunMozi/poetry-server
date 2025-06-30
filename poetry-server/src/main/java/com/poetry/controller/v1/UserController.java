package com.poetry.controller.v1;

import com.poetry.UserControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.dto.UserLoginDTO;
import com.poetry.dto.UserRegisterDTO;
import com.poetry.service.UserService;
import com.poetry.service.VerifyCodeService;
import com.poetry.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserControllerApi {


  private final UserService userService;
  private final VerifyCodeService verifyCodeService;


  @Override
  public ResponseResult<UserVO> login(UserLoginDTO dto) {
    return ResponseResult.ok(userService.login(dto));
  }

  @Override
  public ResponseResult<UserVO> regist(UserRegisterDTO dto) {
    return ResponseResult.ok(userService.regist(dto));
  }

  @Override
  public ResponseResult<?> getCode(Integer type) {
    verifyCodeService.sendVerifyCode(type, null);
    return ResponseResult.ok();
  }

  @Override
  public ResponseResult<?> getCodeForForgetPassword(String place, Integer type) {
    verifyCodeService.sendVerifyCode(type, place);
    return ResponseResult.ok();
  }

  @Override
  public ResponseResult<UserVO> token(@RequestParam("userToken") String userToken) {
    return ResponseResult.ok(userService.token(userToken));
  }


}