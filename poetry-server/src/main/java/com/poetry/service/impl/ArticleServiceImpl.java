package com.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.util.StringUtils;

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
    Page<Article> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());

    QueryWrapper<Article> wrapper = new QueryWrapper<>();
    wrapper.eq("status", 1);

    if (queryDTO.getAuthorId() != null) {
      wrapper.eq("author_id", queryDTO.getAuthorId());
    }

    if (queryDTO.getCategoryId() != null) {
      wrapper.eq("category_id", queryDTO.getCategoryId());
    }

    if (StringUtils.hasText(queryDTO.getKeyword())) {
      wrapper.and(w -> w.like("title", queryDTO.getKeyword())
                        .or()
                        .like("summary", queryDTO.getKeyword()));
    }

    // 排序字段控制：必须是白名单字段
    String sortField = switch (queryDTO.getSort()) {
      case "views" -> "views";
      case "likes" -> "likes";
      case "createTime" -> "create_time";
      default -> "create_time";
    };

    boolean isAsc = "asc".equalsIgnoreCase(queryDTO.getOrder());
    if (isAsc) {
      wrapper.orderByAsc(sortField);
    } else {
      wrapper.orderByDesc(sortField);
    }

    wrapper.select("id", "category_id", "author_id", "title", "slug", "summary",
                   "cover_image", "views", "likes", "comments_count", "create_time");

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
      vo.setAuthorName("Zyan");
      vo.setAuthorAvatar("https://foruda.gitee.com/avatar/1749603480089134020/12783544_yzy_201_1749603480.png");
      vo.setAuthorSourceType(0);
      return vo;
    }).toList();

    return PageResult.of(articlePage.getTotal(), queryDTO.getPage(), queryDTO.getSize(), voList);
  }




}
