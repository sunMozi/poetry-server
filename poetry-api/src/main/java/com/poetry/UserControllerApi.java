package com.poetry;

import com.poetry.common.response.ResponseResult;
import com.poetry.dto.UserLoginDTO;
import com.poetry.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MoZi
 * @createTime 2025/6/29 14:41
 */
@RestController
@RequestMapping("/user")
public interface UserControllerApi {

  @PostMapping("login")
  ResponseResult<UserVO> login(@Valid @RequestBody UserLoginDTO dto);

}
