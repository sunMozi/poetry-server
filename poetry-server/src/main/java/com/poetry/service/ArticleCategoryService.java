package com.poetry.service;

import com.poetry.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * @author Zyan
 * @since 2025-07-02 08:27:21
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

  List<ArticleCategory> active();

  List<ArticleCategory> popular();
}