package com.poetry.service;

import com.poetry.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Zyan
 * @since 2025-07-02 08:27:21
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

  List<ArticleCategory> active();

  List<ArticleCategory> popular();

  Map<Long, String> getCategoryNameMap(Set<Long> categoryIds);
}