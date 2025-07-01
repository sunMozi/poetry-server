package com.poetry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.UserProfile;
import com.poetry.mapper.UserProfileMapper;
import com.poetry.service.UserProfileService;
import com.poetry.vo.ProfileVO;
import com.poetry.vo.SocialLinkVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Zyan
 * @since 2025-07-01 20:06:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements
    UserProfileService {

  private final UserProfileMapper userProfileMapper;

  @Override
  public ProfileVO profile() {
    UserProfile userProfile = userProfileMapper.selectById(1L);
    ProfileVO profileVO = new ProfileVO();
    BeanUtil.copyProperties(userProfile, profileVO, "socialLinks");
    profileVO.setSocialLinks(JSON.parseArray(userProfile.getSocialLinks(), SocialLinkVO.class));
    return profileVO;
  }
}