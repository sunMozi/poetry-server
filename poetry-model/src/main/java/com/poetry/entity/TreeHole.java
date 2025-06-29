package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 树洞
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("tree_hole")
public class TreeHole implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  头像
     */
    @NotBlank(message = "【头像】不能为空")
    private String avatar;

    /**
     *  留言
     */
    @NotBlank(message = "【留言】不能为空")
    private String message;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}