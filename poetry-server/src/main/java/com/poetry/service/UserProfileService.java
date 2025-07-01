package com.poetry.service;

import com.poetry.entity.UserProfile;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.poetry.vo.ProfileVO;
import java.util.List;
/**
 * @author Zyan
 * @since 2025-07-01 20:06:53
 */
public interface UserProfileService extends IService<UserProfile> {

  ProfileVO profile();
}