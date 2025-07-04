package com.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 文章表
 *
 * @author Zyan
 * @since 2025-07-02 11:13:55
 */
@Data
@TableName("article")
public class Article implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   *  主键ID
   */
  private Long id;

  /**
   *  分类ID（逻辑关联 ARTICLE_CATEGORY.ID）
   */
  private Long categoryId;

  /**
   *  作者ID（逻辑关联 USER.ID）
   */
  private Long authorId;

  /**
   *  文章标题
   */
  private String title;

  /**
   *  URL别名，SEO友好
   */
  private String slug;

  /**
   *  摘要（可手写或自动生成）
   */
  private String summary;

  /**
   *  封面图URL
   */
  private String coverImage;

  /**
   *  文章正文（MARKDOWN 或 HTML）
   */
  private String content;

  /**
   *  浏览次数
   */
  private Long views;

  /**
   *  点赞数
   */
  private Long likes;

  /**
   *  评论数
   */
  private Long commentsCount;

  /**
   *  状态：1=发布，0=草稿，-1=逻辑删除
   */
  private Integer status;

  /**
   *  排序值（用于置顶等）
   */
  private Integer sortOrder;

  /**
   *  创建时间
   */
  private LocalDateTime createTime;

  /**
   *  更新时间
   */
  private LocalDateTime updateTime;

}