package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户信息表
 *
 * @author Zyan
 * @since 2025-07-01 20:06:53
 *
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {

}