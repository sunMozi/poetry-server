package com.poetry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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

  @Schema(description = "URL 别名")
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

  @Schema(description = "修改时间")
  private LocalDateTime updateTime;


  @Schema(description = "分类名字")
  private String categoryName;

  @Schema(description = "标签列表")
  private List<String> tagNames;

  @Schema(description = "文章状态 状态：1=发布，0=草稿，-1=逻辑删除")
  private Integer status;

  @Schema(description = "作者ID")
  private Long authorId;

  @Schema(description = "作者名称")
  private String authorName;

  @Schema(description = "作者头像 URL")
  private String authorAvatar;


}
