package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.User;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户信息表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}