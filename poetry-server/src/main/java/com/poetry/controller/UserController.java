package com.poetry.controller;

import com.poetry.UserControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.dto.UserLoginRequestDTO;
import com.poetry.service.UserService;
import com.poetry.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
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


  @Override
  public ResponseResult<UserVO> login(UserLoginRequestDTO dto) {
    return ResponseResult.ok(userService.login(dto));
  }
}