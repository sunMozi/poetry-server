package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Author;
import org.apache.ibatis.annotations.Mapper;
/**
 * 作者表，存储作者基础信息
 *
 * @author Zyan
 * @since 2025-07-02 15:37:30
 *
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

}