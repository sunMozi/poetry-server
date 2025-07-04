package com.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 文章分类表
 *
 * @author Zyan
 * @since 2025-07-02 08:27:21
 */
@Data
@TableName("article_category")
public class ArticleCategory implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   *  主键ID
   */
  private Long id;

  /**
   *  分类名称
   */
  private String sortName;

  /**
   *  图标（表情符号或图标类名）
   */
  private String icon;

  /**
   *  排序值，值越大越靠前
   */
  private Integer sortOrder;

  /**
   *  状态：1=启用，0=禁用
   */
  private Integer status;

  /**
   *  备注说明
   */
  private String remark;

  /**
   *  创建时间
   */
  private LocalDateTime createTime;

  /**
   *  最后更新时间
   */
  private LocalDateTime updateTime;

}