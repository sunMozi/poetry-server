package com.poetry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 文章列表视图对象
 * @author Zyan
 */
@Data
@Schema(description = "文章列表项")
public class ArticleListVO implements Serializable {

  @Schema(description = "文章ID")
  private Long articleId;

  @Schema(description = "文章标题")
  private String title;

  @Schema(description = "URL 别名（SEO友好）")
  private String slug;

  @Schema(description = "文章摘要")
  private String summary;

  @Schema(description = "封面图 URL")
  private String coverImage;

  @Schema(description = "浏览次数")
  private Long views;

  @Schema(description = "点赞数")
  private Long likes;

  @Schema(description = "评论数")
  private Long commentsCount;

  @Schema(description = "创建时间")
  private LocalDateTime createTime;

  @Schema(description = "作者ID")
  private Long authorId;

  @Schema(description = "作者名称")
  private String authorName;

  @Schema(description = "作者头像 URL")
  private String authorAvatar;

  @Schema(description = "作者来源类型：0=内部用户, 1=外部作者, 2=采集作者")
  private Integer authorSourceType;

}
