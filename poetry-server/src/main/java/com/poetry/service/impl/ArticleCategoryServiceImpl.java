package com.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.ArticleCategory;
import com.poetry.mapper.ArticleCategoryMapper;
import com.poetry.service.ArticleCategoryService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Zyan
 * @since 2025-07-02 08:27:21
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleCategoryServiceImpl extends
    ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

  private final ArticleCategoryMapper articleCategoryMapper;

  @Override
  public List<ArticleCategory> active() {
    return articleCategoryMapper.selectList(new LambdaQueryWrapper<ArticleCategory>().eq(
        ArticleCategory::getStatus,
        1).orderByDesc(ArticleCategory::getSortOrder).orderByAsc(ArticleCategory::getId));
  }

  @Override
  public List<ArticleCategory> popular() {
    return articleCategoryMapper.selectList(new LambdaQueryWrapper<ArticleCategory>().eq(
                                                                                         ArticleCategory::getStatus,
                                                                                         1)
                                                                                     .orderByDesc(
                                                                                         ArticleCategory::getSortOrder)
                                                                                     .orderByAsc(
                                                                                         ArticleCategory::getId)
                                                                                     .last("LIMIT 5"));
  }

  @Override
  public Map<Long, String> getCategoryNameMap(Set<Long> categoryIds) {
    if (categoryIds == null || categoryIds.isEmpty()) {
      return Map.of();
    }

    LambdaQueryWrapper<ArticleCategory> wrapper = new LambdaQueryWrapper<>();
    wrapper.in(ArticleCategory::getId, categoryIds);

    List<ArticleCategory> categoryList = articleCategoryMapper.selectList(wrapper);

    return categoryList.stream()
                       .collect(Collectors.toMap(
                           ArticleCategory::getId,
                           ArticleCategory::getSortName
                       ));
  }

}