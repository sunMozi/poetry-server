package com.poetry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/07/13 15:53
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateDTO implements Serializable {

  @NotNull(message = "ID不能为空")
  private Long id;

  @NotBlank(message = "标题不能为空")
  private String title;

  @NotNull(message = "分类ID不能为空")
  private Long categoryId;

  @NotBlank(message = "slug不能为空")
  private String slug;

  @Size(max = 200, message = "摘要最多200字")
  private String summary;

  @NotBlank(message = "封面图片不能为空")
  private String coverImage;


  @NotBlank(message = "内容不能为空")
  private String content;

  @NotNull(message = "状态不能为空")
  private Integer status;

  @NotNull(message = "排序不能为空")
  private Integer sortOrder;

}
