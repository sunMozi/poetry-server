package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.WebInfo;
import org.apache.ibatis.annotations.Mapper;
/**
 * 网站信息表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface WebInfoMapper extends BaseMapper<WebInfo> {

}