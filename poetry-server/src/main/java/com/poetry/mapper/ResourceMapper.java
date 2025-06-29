package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
/**
 * 资源信息
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}