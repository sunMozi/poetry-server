package com.poetry.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author moZiA
 */
@Data
public class ArticleDetailVO {

  // 文章字段
  private Long id;
  private Long categoryId;
  private Long authorId;
  private String title;
  private String slug;
  private String summary;
  private String coverImage;
  private String content;
  private Long views;
  private Long likes;
  private Long commentsCount;
  private Integer status;
  private Integer sortOrder;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  private String authorNickname;
  private String authorRealName;
  private String authorAvatar;
  private String authorBio;
  private String authorEmail;
  private String authorWebsite;
  private Integer authorStatus;
  private LocalDateTime authorCreateTime;
  private LocalDateTime authorUpdateTime;
}