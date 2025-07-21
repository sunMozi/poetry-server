package com.poetry;


import com.poetry.common.response.PageResult;
import com.poetry.common.response.ResponseResult;
import com.poetry.dto.ArticleCreateDTO;
import com.poetry.dto.ArticleQueryDTO;
import com.poetry.dto.ArticleUpdateDTO;
import com.poetry.vo.ArticleDetailVO;
import com.poetry.vo.ArticleListVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/2 11:56
 * @description
 */
@RestController
@RequestMapping("/article")
public interface ArticleControllerApi {

  @RequestMapping("/list")
  ResponseResult<PageResult<ArticleListVO>> list(ArticleQueryDTO queryDTO);

  @GetMapping("/detail/{slug}")
  ResponseResult<ArticleDetailVO> getDetailBySlug(@PathVariable("slug") String slug);

  @PostMapping("/create")
  ResponseResult<Void> create(@RequestBody ArticleCreateDTO dto);

  @PutMapping
  ResponseResult<Void> put(@RequestBody ArticleUpdateDTO dto);

  @GetMapping("/{id}")
  ResponseResult<ArticleDetailVO> getDetailById(@PathVariable("id") Long id);

}