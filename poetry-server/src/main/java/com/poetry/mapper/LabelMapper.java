package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Label;
import org.apache.ibatis.annotations.Mapper;
/**
 * 标签
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface LabelMapper extends BaseMapper<Label> {

}