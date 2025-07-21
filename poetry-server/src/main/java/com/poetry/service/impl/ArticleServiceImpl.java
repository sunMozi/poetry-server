package com.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.common.exception.Exceptions;
import com.poetry.common.response.PageResult;
import com.poetry.common.utils.SlugUtils;
import com.poetry.dto.ArticleCreateDTO;
import com.poetry.dto.ArticleQueryDTO;
import com.poetry.dto.ArticleUpdateDTO;
import com.poetry.entity.Article;
import com.poetry.entity.Author;
import com.poetry.mapper.ArticleMapper;
import com.poetry.mapper.AuthorMapper;
import com.poetry.service.ArticleCategoryService;
import com.poetry.service.ArticleService;
import com.poetry.vo.ArticleDetailVO;
import com.poetry.vo.ArticleListVO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
  private final ArticleCategoryService articleCategoryService;


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
  public Void create(ArticleCreateDTO createDTO) {
    Article article = new Article();
    BeanUtils.copyProperties(createDTO, article);

    String baseSlug;
    if (StringUtils.hasText(createDTO.getSlug())) {
      baseSlug = SlugUtils.toSlug(createDTO.getSlug());
    } else {
      baseSlug = SlugUtils.toSlug(createDTO.getTitle());
    }

    String uniqueSlug = SlugUtils.generateUniqueSlug(baseSlug, articleMapper::existsBySlug);
    article.setSlug(uniqueSlug);

    article.setAuthorId(1L);
    article.setViews(0L);
    article.setLikes(0L);
    article.setCommentsCount(0L);
    article.setCreateTime(LocalDateTime.now());
    article.setUpdateTime(LocalDateTime.now());

    articleMapper.insert(article);

    return null;
  }

  @Override
  public void put(ArticleUpdateDTO dto) {

  }

  @Override
  public ArticleDetailVO getDetailById(Long id) {
    Article article = articleMapper.selectById(id);

    if (article == null) {
      log.warn("文章未找到，id={}", id);
      Exceptions.cast(404, "文章未找到");
    }

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
    QueryWrapper<Article> wrapper = buildQueryWrapper(queryDTO);

    Page<Article> articlePage = articleMapper.selectPage(page, wrapper);
    List<Article> articles = articlePage.getRecords();

    if (articles.isEmpty()) {
      return PageResult.of(0L, queryDTO.getPage(), queryDTO.getSize(), List.of());
    }

    return PageResult.of(articlePage.getTotal(),
                         queryDTO.getPage(),
                         queryDTO.getSize(),
                         buildListVOs(articles));
  }

  private QueryWrapper<Article> buildQueryWrapper(ArticleQueryDTO dto) {
    QueryWrapper<Article> wrapper = new QueryWrapper<>();

    if (dto.getAuthorId() != null) {
      wrapper.eq("author_id", dto.getAuthorId());
    }

    if (dto.getCategoryId() != null) {
      wrapper.eq("category_id", dto.getCategoryId());
    }

    if (StringUtils.hasText(dto.getKeyword())) {
      wrapper.and(w -> w.like("title", dto.getKeyword()).or().like("summary", dto.getKeyword()));
    }

    // 排序字段白名单控制
    String sortField = switch (dto.getSort()) {
      case "views" -> "views";
      case "likes" -> "likes";
      case "createTime" -> "create_time";
      default -> "create_time";
    };

    if ("asc".equalsIgnoreCase(dto.getOrder())) {
      wrapper.orderByAsc(sortField);
    } else {
      wrapper.orderByDesc(sortField);
    }

    return wrapper;
  }


  private List<ArticleListVO> buildListVOs(List<Article> articles) {
    Set<Long> categoryIds = articles.stream()
                                    .map(Article::getCategoryId)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toSet());

    Map<Long, String> categoryMap = articleCategoryService.getCategoryNameMap(categoryIds);
    Map<Long, String> authorNameMap = Map.of(1L, "Zyan");
    Map<Long, String> authorAvatarMap = Map.of(1L,
                                               "https://foruda.gitee.com/avatar/1749603480089134020/12783544_yzy_201_1749603480.png");

    return articles.stream().map(article -> {
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
      vo.setUpdateTime(article.getUpdateTime());
      vo.setCategoryName(categoryMap.get(article.getCategoryId()));
      vo.setAuthorId(article.getAuthorId());
      vo.setAuthorName(authorNameMap.getOrDefault(article.getAuthorId(), "未知作者"));
      vo.setAuthorAvatar(authorAvatarMap.getOrDefault(article.getAuthorId(), null));
      vo.setStatus(article.getStatus());
      vo.setTagNames(List.of());
      return vo;
    }).toList();
  }


}
