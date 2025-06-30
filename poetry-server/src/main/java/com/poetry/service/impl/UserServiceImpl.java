package com.poetry.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.common.constants.CommonConst;
import com.poetry.common.context.BaseContext;
import com.poetry.common.core.pojo.TokenPayload;
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
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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

    if (dto.isAdmin()) {
      cacheAdminToken(user);
    } else {
      cacheUserToken(user);
    }

    return build;
  }

  @Override
  @Transactional
  public UserVO regist(UserRegisterDTO dto) {
    validateRegisterParams(dto);

    checkVerificationCode(dto);

    checkUserUniqueness(dto);

    // 构建用户实体
    User user = buildUserEntity(dto, DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));

    save(user);
    String token = tokenUtil.generateUserToken(user.getId(), user.getUserType());

    UserVO userVO = buildUserVO(user, token);
    userVO.setAccessToken(token);
    cacheRegisterToken(user);

    // TODO 初始化用户社交关系
    // initUserSocialData(user);

    // 构建返回对象
    return userVO;
  }

  @Override
  public UserVO token(String userToken) {
    if (userToken == null || userToken.isBlank()) {
      log.warn("token 登录失败: token 为空");
      Exceptions.cast(401, "token 无效或已过期");
    }

    Claims claims = tokenUtil.parseTokenSafe(userToken);
    if (claims == null) {
      log.warn("token 登录失败: 无法解析 token");
      Exceptions.cast(401, "token 无效或已过期");
    }

    Long userId = claims.get("userId", Long.class);
    if (userId == null) {
      log.warn("token 登录失败: token 中无 userId");
      Exceptions.cast(401, "token 信息不完整");
    }

    User user = userMapper.selectById(userId);
    if (user == null) {
      log.warn("token 登录失败: 数据库中不存在该用户 userId={}", userId);
      Exceptions.cast(401, "不存在该用户");
    }

    // 脱敏封装返回
    UserVO userVO = buildUserVO(user, userToken);
    log.info("token 登录成功: userId={}", userId);
    return userVO;
  }

  @Override
  public void logout() {
    TokenPayload tokenPayload= BaseContext.get();
    redisUtil.delete(RedisKeyUtil.userTokenKey(tokenPayload.getUserId()));
  }


  /**
   * 校验基本参数
   */
  private void validateRegisterParams(UserRegisterDTO dto) {
    if (dto.getUsername().matches("\\d{11}")) {
      Exceptions.cast(400, "用户名不能为11位数字！");
    }
    if (dto.getUsername().contains("@")) {
      Exceptions.cast(400, "用户名不能包含@！");
    }
    if (StringUtils.hasText(dto.getPhoneNumber()) && StringUtils.hasText(dto.getEmail())) {
      Exceptions.cast(400, "手机号与邮箱只能选择其中一个！");
    }
    if (!StringUtils.hasText(dto.getPhoneNumber()) && !StringUtils.hasText(dto.getEmail())) {
      Exceptions.cast(400, "请输入邮箱或手机号");
    }
  }

  /**
   * 校验验证码
   */
  private void checkVerificationCode(UserRegisterDTO dto) {
    String key = RedisKeyUtil.verifyCodeKey(dto);

    String codeCache = redisUtil.get(key);
    if (codeCache == null || !codeCache.equals(dto.getCode())) {
      Exceptions.cast(400, "验证码错误！");
    }
    redisUtil.delete(key);
  }

  /**
   * 校验用户名、手机号、邮箱唯一性
   */
  private void checkUserUniqueness(UserRegisterDTO dto) {
    if (lambdaQuery().eq(User::getUsername, dto.getUsername()).count() > 0) {
      Exceptions.cast(400, "用户名重复！");
    }
    if (StringUtils.hasText(dto.getPhoneNumber()) &&
        lambdaQuery().eq(User::getPhoneNumber, dto.getPhoneNumber()).count() > 0) {
      Exceptions.cast(400, "手机号重复！");
    }
    if (StringUtils.hasText(dto.getEmail()) &&
        lambdaQuery().eq(User::getEmail, dto.getEmail()).count() > 0) {
      Exceptions.cast(400, "邮箱重复！");
    }
  }

  /**
   * 构建用户实体
   */
  private User buildUserEntity(UserRegisterDTO dto, String encryptedPassword) {
    User user = new User();
    user.setUsername(dto.getUsername());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setEmail(dto.getEmail());
    user.setPassword(encryptedPassword);
    user.setAvatar(CommonConst.DEFAULT_AVATAR);
    user.setUserType(UserTypeEnum.USER.getCode());
    return user;
  }


  /**
   * 缓存登录 Token
   */
  private void cacheUserToken(User user) {
    redisUtil.set(RedisKeyUtil.userTokenKey(user.getId()),
                  JSON.toJSONString(user),
                  CommonConst.USER_TOKEN_INTERVAL);
  }

  private void cacheAdminToken(User user) {
    redisUtil.set(RedisKeyUtil.adminTokenKey(user.getId()),
                  JSON.toJSONString(user),
                  CommonConst.USER_TOKEN_INTERVAL);
  }


  /**
   * 缓存注册 Token
   */
  private void cacheRegisterToken(User user) {
    TokenPayload payload = new TokenPayload(user.getId(), user.getUserType(), "register");
    String key = RedisKeyUtil.userTokenKey(user.getId());
    redisUtil.set(key, JSON.toJSONString(payload), CommonConst.USER_TOKEN_INTERVAL);
  }

  /**
   * 构建 UserVO
   */
  private UserVO buildUserVO(User user, String token) {
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(user, userVO);
    userVO.setAccessToken(token);
    return userVO;
  }


}