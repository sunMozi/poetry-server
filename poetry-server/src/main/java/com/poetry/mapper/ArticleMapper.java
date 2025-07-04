package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Article;
import org.apache.ibatis.annotations.Mapper;
/**
 * 文章表
 *
 * @author Zyan
 * @since 2025-07-02 11:13:55
 *
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}