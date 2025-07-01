package com.poetry.vo;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 网站主页公告通知VO
 */
@Data
public class SiteNoticeVO {

  private String title;
  private String content;
  private String type;
  private Boolean isActive;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
