package com.poetry.controller.v1;


import com.poetry.ArticleControllerApi;
import com.poetry.common.response.PageResult;
import com.poetry.common.response.ResponseResult;
import com.poetry.dto.ArticleQueryDTO;
import com.poetry.service.ArticleService;
import com.poetry.vo.ArticleDetailVO;
import com.poetry.vo.ArticleListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/2 12:00
 * @description
 */
@RestController
@RequiredArgsConstructor
public class ArticleController implements ArticleControllerApi {

  private final ArticleService articleService;


  @Override
  public ResponseResult<PageResult<ArticleListVO>> list(ArticleQueryDTO queryDTO) {
    return ResponseResult.ok(articleService.getList(queryDTO));
  }

  @Override
  public ResponseResult<ArticleDetailVO> getDetailBySlug(String slug) {
    return ResponseResult.ok(articleService.getDetailBySlug(slug));
  }
}