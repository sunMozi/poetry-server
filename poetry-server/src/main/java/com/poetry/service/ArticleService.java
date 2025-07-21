package com.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.poetry.common.response.PageResult;
import com.poetry.dto.ArticleCreateDTO;
import com.poetry.dto.ArticleQueryDTO;
import com.poetry.dto.ArticleUpdateDTO;
import com.poetry.entity.Article;
import com.poetry.vo.ArticleDetailVO;
import com.poetry.vo.ArticleListVO;

/**
 * @author Zyan
 * @since 2025-07-02 11:13:55
 */
public interface ArticleService extends IService<Article> {

  PageResult<ArticleListVO> getList(ArticleQueryDTO queryDTO);

  ArticleDetailVO getDetailBySlug(String slug);

  Void create(ArticleCreateDTO createDTO);

  void put(ArticleUpdateDTO dto);

  ArticleDetailVO getDetailById(Long id);
}