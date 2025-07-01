package com.poetry.vo;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 社交链接值对象
 * @author moZiA
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocialLinkVO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * 平台标签
   */
  private String label;

  /**
   * 链接 URL
   */
  private String url;

  /**
   * 图标
   */
  private String icon;
}
