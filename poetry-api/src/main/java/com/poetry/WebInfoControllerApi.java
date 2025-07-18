package com.poetry;

import com.poetry.common.response.ResponseResult;
import com.poetry.vo.ProfileVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MoZi
 * @createTime 2025/6/30 15:58
 */
@RestController
@RequestMapping("/webInfo")
public interface WebInfoControllerApi {


  @GetMapping("profile")
  ResponseResult<ProfileVO> profile();


}
