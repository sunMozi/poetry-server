package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 微言表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("wei_yan")
public class WeiYan implements Serializable{

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
     *  点赞数
     */
    @NotBlank(message = "【点赞数】不能为空")
    private Integer likeCount;

    /**
     *  内容
     */
    @NotBlank(message = "【内容】不能为空")
    private String content;

    /**
     *  类型
     */
    @NotBlank(message = "【类型】不能为空")
    private String type;

    /**
     *  来源标识
     */
    @NotBlank(message = "【来源标识】不能为空")
    private Integer source;

    /**
     *  是否公开[0:仅自己可见，1:所有人可见]
     */
    @NotBlank(message = "【是否公开[0:仅自己可见，1:所有人可见]】不能为空")
    private Integer isPublic;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}