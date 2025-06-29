package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Family;
import org.apache.ibatis.annotations.Mapper;
/**
 * 家庭信息
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface FamilyMapper extends BaseMapper<Family> {

}