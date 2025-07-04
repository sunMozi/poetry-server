package com.poetry.controller.v1;


import com.poetry.ArticleCategoryControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.entity.ArticleCategory;
import com.poetry.service.ArticleCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/2 8:35
 * @description
 */
@RestController
@RequiredArgsConstructor
public class ArticleCategoryController implements ArticleCategoryControllerApi {


  private final ArticleCategoryService articleCategoryService;


  @Override
  public ResponseResult<List<ArticleCategory>> all() {
    return ResponseResult.ok(articleCategoryService.list());
  }

  @Override
  public ResponseResult<List<ArticleCategory>> active() {
    return ResponseResult.ok(articleCategoryService.active());
  }

  @Override
  public ResponseResult<List<ArticleCategory>> popular() {
    return ResponseResult.ok(articleCategoryService.popular());
  }
}