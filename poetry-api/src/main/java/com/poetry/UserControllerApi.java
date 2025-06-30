package com.poetry;

import com.poetry.common.response.ResponseResult;
import com.poetry.dto.UserLoginDTO;
import com.poetry.dto.UserRegisterDTO;
import com.poetry.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MoZi
 * @createTime 2025/6/29 14:41
 */
@RestController
@RequestMapping("/user")
public interface UserControllerApi {

  @PostMapping("login")
  ResponseResult<UserVO> login(@Valid UserLoginDTO dto);

  @PostMapping("regist")
  ResponseResult<UserVO> regist(@Valid UserRegisterDTO dto);

  @GetMapping("getCode")
  ResponseResult<?> getCode(Integer type);

  @GetMapping("/getCodeForForgetPassword")
  ResponseResult<?> getCodeForForgetPassword(@RequestParam("place") String place,
                                             @RequestParam("flag") Integer type);

  @PostMapping("token")
  ResponseResult<UserVO> token(String userToken);


  @PostMapping("logout")
  ResponseResult<?> logout();

}
