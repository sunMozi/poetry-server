package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * 文章表
 *
 * @author Zyan
 * @since 2025-07-02 11:13:55
 *
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


  @Select("SELECT CASE WHEN COUNT(1) > 0 THEN TRUE ELSE FALSE END FROM article WHERE slug = #{slug}")
  boolean existsBySlug(@Param("slug") String slug);

}