package com.poetry.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.common.constants.CommonConst;
import com.poetry.common.exception.Exceptions;
import com.poetry.common.utils.RedisKeyUtil;
import com.poetry.common.utils.RedisUtil;
import com.poetry.common.utils.TokenUtil;
import com.poetry.dto.UserLoginDTO;
import com.poetry.entity.User;
import com.poetry.enums.DeletedEnum;
import com.poetry.enums.UserStatusEnum;
import com.poetry.mapper.UserMapper;
import com.poetry.service.UserService;
import com.poetry.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Zyan
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
    // 1. 根据邮箱查询用户（邮箱登录为唯一入口）
    User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                                             .eq(User::getEmail, dto.getAccount())
                                             .eq(User::getDeleted,
                                                 DeletedEnum.NOT_DELETED.getCode())
                                             .last("LIMIT 1"));

    System.out.println("user: " + user);

    if (user == null) {
      Exceptions.cast(407, "账号或密码错误");
    }

    // 2. 校验用户状态
    validateUserStatus(user);
    System.out.println(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));

    // 3. 校验密码
    String encryptedInput = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
    if (!encryptedInput.equals(user.getPassword())) {
      Exceptions.cast(407, "账号或密码错误");
    }

    // 4. 生成访问令牌
    String token = tokenUtil.generateUserToken(user.getId(), user.getUserType());

    // 5. 构造视图对象
    UserVO vo = buildUserVO(user, token);

    // 6. 缓存用户数据
    cacheUserToRedis(user);

    return vo;
  }


  private void validateUserStatus(User user) {
    if (user.getUserStatus() == UserStatusEnum.DISABLED.getCode()) {
      Exceptions.cast(406, "账号已被锁定，请联系管理员");
    }

    if (user.getDeleted() == DeletedEnum.DELETED.getCode()) {
      Exceptions.cast(406, "账号已被删除，请联系管理员");
    }
  }

  private UserVO buildUserVO(User user, String token) {
    return UserVO.builder()
                 .id(user.getId())
                 .username(user.getUsername())
                 .phoneNumber(user.getPhoneNumber())
                 .email(user.getEmail())
                 .avatar(user.getAvatar())
                 .gender(user.getGender())
                 .introduction(user.getIntroduction())
                 .accessToken(token)
                 .build();
  }

  private void cacheUserToRedis(User user) {
    String key = RedisKeyUtil.userTokenKey(user.getId());
    String value = JSON.toJSONString(user);
    redisUtil.set(key, value, CommonConst.USER_TOKEN_INTERVAL);
  }


}