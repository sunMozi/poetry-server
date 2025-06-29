package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 文章评论表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("comment")
public class Comment implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  评论来源标识
     */
    @NotBlank(message = "【评论来源标识】不能为空")
    private Integer source;

    /**
     *  评论来源类型
     */
    @NotBlank(message = "【评论来源类型】不能为空")
    private String type;

    /**
     *  父评论ID
     */
    @NotBlank(message = "【父评论ID】不能为空")
    private Integer parentCommentId;

    /**
     *  发表用户ID
     */
    @NotBlank(message = "【发表用户ID】不能为空")
    private Integer userId;

    /**
     *  楼层评论ID
     */
    @NotBlank(message = "【楼层评论ID】不能为空")
    private Integer floorCommentId;

    /**
     *  父发表用户名ID
     */
    @NotBlank(message = "【父发表用户名ID】不能为空")
    private Integer parentUserId;

    /**
     *  点赞数
     */
    @NotBlank(message = "【点赞数】不能为空")
    private Integer likeCount;

    /**
     *  评论内容
     */
    @NotBlank(message = "【评论内容】不能为空")
    private String commentContent;

    /**
     *  评论额外信息
     */
    @NotBlank(message = "【评论额外信息】不能为空")
    private String commentInfo;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}