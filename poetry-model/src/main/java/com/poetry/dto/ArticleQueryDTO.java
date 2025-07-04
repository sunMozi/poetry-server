package com.poetry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;


/**
 * 文章列表查询参数
 * @author moZiA
 */
@Data
@Schema(description = "文章列表查询参数")
public class ArticleQueryDTO {

  @Schema(description = "页码，从1开始", example = "1")
  @Min(value = 1, message = "页码必须大于等于1")
  private Integer page = 1;

  @Schema(description = "每页条数，最大不超过100", example = "10")
  @Min(value = 1, message = "每页条数必须大于等于1")
  @Max(value = 100, message = "每页条数不能超过100")
  private Integer size = 10;

  @Schema(description = "文章分类ID", example = "1001")
  private Long categoryId;

  @Schema(description = "作者ID", example = "5001")
  private Long authorId;

  @Schema(description = "标题或摘要关键字，模糊查询", example = "Spring")
  private String keyword;

  @Schema(description = "排序字段（views、likes、createTime）", example = "createTime")
  private String sort = "createTime";

  @Schema(description = "排序方式（asc、desc）", example = "desc")
  private String order = "desc";
}
