package com.poetry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author moZiA
 */
@Data
@Schema(description = "系统参数视图对象")
public class WebInfoVO {

  @Schema(description = "配置 ID")
  private Long id;

  @Schema(description = "网站名称")
  private String webName;

  @Schema(description = "网站标题")
  private String webTitle;

  @Schema(description = "公告")
  private String notices;

  @Schema(description = "页脚")
  private String footer;

  @Schema(description = "背景图片 URL")
  private String backgroundImage;

  @Schema(description = "站点头像 URL")
  private String avatar;

  @Schema(description = "随机封面 URL")
  private String randomCover;

  @Schema(description = "是否启用（true=启用，false=禁用）")
  private Boolean status;

  @Schema(description = "历史总访问数")
  private String historyAllCount;

  @Schema(description = "历史日访问数")
  private String historyDayCount;

  @Schema(description = "默认存储类型")
  private String defaultStoreType;
}
