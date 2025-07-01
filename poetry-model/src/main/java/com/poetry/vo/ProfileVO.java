package com.poetry.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户个人资料值对象
 * <p>不可变对象，用于数据传输</p>
 * @author moZiA
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileVO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * 用户头像地址
   */
  private String avatarUrl;

  /**
   * 用户名称
   */
  private String name;

  /**
   * 用户简介
   */
  private String bio;

  /**
   * 文章数量
   */
  private int articlesCount;

  /**
   * 分类数量
   */
  private int categoriesCount;

  /**
   * 浏览量
   */
  private int views;

  /**
   * 社交链接列表
   */
  private List<SocialLinkVO> socialLinks;


}
