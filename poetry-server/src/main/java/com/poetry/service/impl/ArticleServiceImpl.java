package com.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.common.exception.Exceptions;
import com.poetry.common.response.PageResult;
import com.poetry.dto.ArticleQueryDTO;
import com.poetry.entity.Article;
import com.poetry.entity.Author;
import com.poetry.mapper.ArticleMapper;
import com.poetry.mapper.AuthorMapper;
import com.poetry.service.ArticleService;
import com.poetry.vo.ArticleDetailVO;
import com.poetry.vo.ArticleListVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Zyan
 * @since 2025-07-02 11:13:55
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements
    ArticleService {

  private final ArticleMapper articleMapper;
  private final AuthorMapper authorMapper;


  @Override
  public ArticleDetailVO getDetailBySlug(String slug) {
    // 1. 根据 slug 查询文章
    Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>().eq(Article::getSlug,
                                                                                   slug)
                                                                               .eq(Article::getStatus,
                                                                                   1));

    if (article == null) {
      log.warn("文章未找到，slug={}", slug);
      Exceptions.cast(404, "文章未找到");
    }

    // 2. 根据文章的 authorId 查询作者信息
    Author author = authorMapper.selectById(article.getAuthorId());

    // 3. 构造返回 VO
    ArticleDetailVO vo = new ArticleDetailVO();
    BeanUtils.copyProperties(article, vo);

    if (author != null) {
      vo.setAuthorNickname(author.getNickname());
      vo.setAuthorRealName(author.getRealName());
      vo.setAuthorAvatar(author.getAvatar());
      vo.setAuthorBio(author.getBio());
      vo.setAuthorEmail(author.getEmail());
      vo.setAuthorWebsite(author.getWebsite());
      vo.setAuthorStatus(author.getStatus());
      vo.setAuthorCreateTime(author.getCreateTime());
      vo.setAuthorUpdateTime(author.getUpdateTime());
    } else {
      log.warn("作者信息未找到，authorId={}", article.getAuthorId());
    }

    return vo;
  }

  @Override
  public PageResult<ArticleListVO> getList(ArticleQueryDTO queryDTO) {
    // 1. 构建分页参数
    Page<Article> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

    // 2. 构建查询条件
    LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Article::getStatus, 1);
    wrapper.eq(Article::getAuthorId, queryDTO.getAuthorId() != null ? queryDTO.getAuthorId() : 1L);

    if (queryDTO.getCategoryId() != null) {
      wrapper.eq(Article::getCategoryId, queryDTO.getCategoryId());
    }
    if (queryDTO.getAuthorId() != null) {
      wrapper.eq(Article::getAuthorId, queryDTO.getAuthorId());
    }
    if (queryDTO.getKeyword() != null && !queryDTO.getKeyword().isEmpty()) {
      wrapper.and(w -> w.like(Article::getTitle, queryDTO.getKeyword())
                        .or()
                        .like(Article::getSummary, queryDTO.getKeyword()));
    }

    wrapper.select(Article::getId,
                   Article::getCategoryId,
                   Article::getAuthorId,
                   Article::getTitle,
                   Article::getSlug,
                   Article::getSummary,
                   Article::getCoverImage,
                   Article::getViews,
                   Article::getLikes,
                   Article::getCommentsCount,
                   Article::getCreateTime);

    Page<Article> articlePage = articleMapper.selectPage(page, wrapper);

    List<ArticleListVO> voList = articlePage.getRecords().stream().map(article -> {
      ArticleListVO vo = new ArticleListVO();
      vo.setArticleId(article.getId());
      vo.setTitle(article.getTitle());
      vo.setSlug(article.getSlug());
      vo.setSummary(article.getSummary());
      vo.setCoverImage(article.getCoverImage());
      vo.setViews(article.getViews());
      vo.setLikes(article.getLikes());
      vo.setCommentsCount(article.getCommentsCount());
      vo.setCreateTime(article.getCreateTime());
      vo.setAuthorId(article.getAuthorId());

      vo.setAuthorName("作者名称-待填充");
      vo.setAuthorAvatar("作者头像URL-待填充");
      vo.setAuthorSourceType(0);

      return vo;
    }).toList();

    return PageResult.of(articlePage.getTotal(), queryDTO.getPage(), queryDTO.getSize(), voList);
  }


}
