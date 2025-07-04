package com.poetry;

import com.poetry.common.response.ResponseResult;
import com.poetry.entity.ArticleCategory;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MoZi
 * @createTime 2025/7/2 8:29
 */
@RestController
@RequestMapping("category")
public interface ArticleCategoryControllerApi {


  @RequestMapping("/all")
  ResponseResult<List<ArticleCategory>> all();

  @RequestMapping("/active")
  ResponseResult<List<ArticleCategory>> active();

  @RequestMapping("/popular")
  ResponseResult<List<ArticleCategory>> popular();


}
