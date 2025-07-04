package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;
/**
 * 文章分类表
 *
 * @author Zyan
 * @since 2025-07-02 08:27:21
 *
 */
@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

}