package com.poetry.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.common.constants.CommonConst;
import com.poetry.common.enums.DeletedEnum;
import com.poetry.common.enums.UserStatusEnum;
import com.poetry.common.enums.UserTypeEnum;
import com.poetry.common.exception.Exceptions;
import com.poetry.common.utils.RedisKeyUtil;
import com.poetry.common.utils.RedisUtil;
import com.poetry.common.utils.TokenUtil;
import com.poetry.dto.UserLoginDTO;
import com.poetry.dto.UserRegisterDTO;
import com.poetry.entity.User;
import com.poetry.mapper.UserMapper;
import com.poetry.service.UserService;
import com.poetry.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author system
 * @since 2025-06-29 11:49:33
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


  private final UserMapper userMapper;
  private final RedisUtil redisUtil;
  private final TokenUtil tokenUtil;

  @Override
  public UserVO login(UserLoginDTO dto) {
    String encryptedPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());

    User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                                             .eq(User::getPassword, encryptedPassword)
                                             .and(wrapper -> wrapper.eq(User::getUsername,
                                                                        dto.getAccount())
                                                                    .or()
                                                                    .eq(User::getPhoneNumber,
                                                                        dto.getAccount())
                                                                    .or()
                                                                    .eq(User::getEmail,
                                                                        dto.getAccount())));

    if (user == null) {
      Exceptions.cast(407, "账号或密码错误");
    }

    if (user.getUserStatus() == UserStatusEnum.DISABLED.getCode()) {
      Exceptions.cast(406, "账号已被锁定，请联系管理员");
    }

    if (user.getDeleted() == DeletedEnum.DELETED.getCode()) {
      Exceptions.cast(406, "账号已被删除，请联系管理员");
    }

    if (dto.isAdmin()) {
      if (user.getUserType() != UserTypeEnum.ADMIN.getCode() &&
          user.getUserType() != UserTypeEnum.MANAGER.getCode()) {
        Exceptions.cast(406, "账号权限不足");
      }
    }

    String token = tokenUtil.generateUserToken(user.getId(), user.getUserType());

    UserVO build = UserVO.builder()
                         .id(user.getId())
                         .username(user.getUsername())
                         .phoneNumber(user.getPhoneNumber())
                         .email(user.getEmail())
                         .avatar(user.getAvatar())
                         .gender(user.getGender())
                         .introduction(user.getIntroduction())
                         .subscribe(user.getSubscribe())
                         .accessToken(token)
                         .build();

    redisUtil.set(dto.isAdmin() ? RedisKeyUtil.adminTokenKey(user.getId())
                      : RedisKeyUtil.userTokenKey(user.getId()),
                  JSON.toJSONString(build),
                  CommonConst.USER_TOKEN_INTERVAL);

    return build;
  }

  @Override
  public UserVO regist(UserRegisterDTO dto) {
    if (StringUtils.hasText(dto.getPhoneNumber()) && StringUtils.hasText(dto.getEmail())) {
      Exceptions.cast(400, "手机号与邮箱只能选择其中一个！");
    }
    if (!StringUtils.hasText(dto.getPhoneNumber()) && !StringUtils.hasText(dto.getEmail())) {
      Exceptions.cast(400, "请输入邮箱或手机号");
    }

    return null;
  }


}