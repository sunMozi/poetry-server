package com.poetry.entity;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("article")
public class Article implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  用户ID
     */
    @NotBlank(message = "【用户ID】不能为空")
    private Integer userId;

    /**
     *  分类ID
     */
    @NotBlank(message = "【分类ID】不能为空")
    private Integer sortId;

    /**
     *  标签ID
     */
    @NotBlank(message = "【标签ID】不能为空")
    private Integer labelId;

    /**
     *  封面
     */
    @NotBlank(message = "【封面】不能为空")
    private String articleCover;

    /**
     *  博文标题
     */
    @NotBlank(message = "【博文标题】不能为空")
    private String articleTitle;

    /**
     *  博文内容
     */
    @NotBlank(message = "【博文内容】不能为空")
    private String articleContent;

    /**
     *  视频链接
     */
    @NotBlank(message = "【视频链接】不能为空")
    private String videoUrl;

    /**
     *  浏览量
     */
    @NotBlank(message = "【浏览量】不能为空")
    private Integer viewCount;

    /**
     *  点赞数
     */
    @NotBlank(message = "【点赞数】不能为空")
    private Integer likeCount;

    /**
     *  是否可见[0:否，1:是]
     */
    @NotBlank(message = "【是否可见[0:否，1:是]】不能为空")
    private Integer viewStatus;

    /**
     *  密码
     */
    @NotBlank(message = "【密码】不能为空")
    private String password;

    /**
     *  提示
     */
    @NotBlank(message = "【提示】不能为空")
    private String tips;

    /**
     *  是否推荐[0:否，1:是]
     */
    @NotBlank(message = "【是否推荐[0:否，1:是]】不能为空")
    private Integer recommendStatus;

    /**
     *  是否启用评论[0:否，1:是]
     */
    @NotBlank(message = "【是否启用评论[0:否，1:是]】不能为空")
    private Integer commentStatus;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

    /**
     *  最终修改时间
     */
    @NotBlank(message = "【最终修改时间】不能为空")
    private Date updateTime;

    /**
     *  最终修改人
     */
    @NotBlank(message = "【最终修改人】不能为空")
    private String updateBy;

    /**
     *  是否启用[0:未删除，1:已删除]
     */
    @NotBlank(message = "【是否启用[0:未删除，1:已删除]】不能为空")
    private Integer deleted;

}