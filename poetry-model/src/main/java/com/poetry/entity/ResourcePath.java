package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 资源聚合
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("resource_path")
public class ResourcePath implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  标题
     */
    @NotBlank(message = "【标题】不能为空")
    private String title;

    /**
     *  分类
     */
    @NotBlank(message = "【分类】不能为空")
    private String classify;

    /**
     *  封面
     */
    @NotBlank(message = "【封面】不能为空")
    private String cover;

    /**
     *  链接
     */
    @NotBlank(message = "【链接】不能为空")
    private String url;

    /**
     *  简介
     */
    @NotBlank(message = "【简介】不能为空")
    private String introduction;

    /**
     *  资源类型
     */
    @NotBlank(message = "【资源类型】不能为空")
    private String type;

    /**
     *  是否启用[0:否，1:是]
     */
    @NotBlank(message = "【是否启用[0:否，1:是]】不能为空")
    private Integer status;

    /**
     *  备注
     */
    @NotBlank(message = "【备注】不能为空")
    private String remark;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}