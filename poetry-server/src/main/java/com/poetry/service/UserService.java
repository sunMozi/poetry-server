package com.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.poetry.dto.UserLoginDTO;
import com.poetry.entity.User;
import com.poetry.vo.UserVO;

/**
 * @author system
 * @since 2025-06-29 11:49:33
 */
public interface UserService extends IService<User> {

  UserVO login(UserLoginDTO dto);


}