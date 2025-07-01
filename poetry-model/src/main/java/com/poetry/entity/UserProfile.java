package com.poetry.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息表
 *
 * @author Zyan
 * @since 2025-07-01 20:06:53
 */
@Data
@TableName("user_profile")
public class UserProfile implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  用户唯一标识
     */
    private Long id;

    /**
     *  头像URL
     */
    private String avatarUrl;

    /**
     *  用户名
     */
    private String name;

    /**
     *  个人简介
     */
    private String bio;

    /**
     *  文章数量
     */
    private Integer articlesCount;

    /**
     *  分类数量
     */
    private Integer categoriesCount;

    /**
     *  浏览量
     */
    private Long views;

    /**
     *  社交链接(JSON格式)
     */
    private String socialLinks;

    /**
     *  创建时间
     */
    private LocalDateTime createdAt;

    /**
     *  更新时间
     */
    private LocalDateTime updatedAt;

}