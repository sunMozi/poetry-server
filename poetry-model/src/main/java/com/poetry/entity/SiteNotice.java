package com.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 网站主页公告通知表
 *
 * @author Zyan
 * @since 2025-07-01 20:56:44
 */
@Data
@TableName("site_notice")
public class SiteNotice implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   *  公告ID
   */
  private Long id;

  /**
   *  公告标题
   */
  private String title;

  /**
   *  公告内容，支持HTML格式
   */
  private String content;

  /**
   *  公告类型
   */
  private String type;

  /**
   *  是否激活显示
   */
  private Integer isActive;

  /**
   *  公告开始时间
   */
  private Date startTime;

  /**
   *  公告结束时间，空表示永久有效
   */
  private Date endTime;

  /**
   *  创建时间
   */
  private Date createTime;

  /**
   *  更新时间
   */
  private Date updateTime;

}