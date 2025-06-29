package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 资源信息
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("resource")
public class Resource implements Serializable{

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
     *  资源类型
     */
    @NotBlank(message = "【资源类型】不能为空")
    private String type;

    /**
     *  资源路径
     */
    @NotBlank(message = "【资源路径】不能为空")
    private String path;

    /**
     *  资源内容的大小，单位：字节
     */
    @NotBlank(message = "【资源内容的大小，单位：字节】不能为空")
    private Integer size;

    /**
     *  文件名称
     */
    @NotBlank(message = "【文件名称】不能为空")
    private String originalName;

    /**
     *  资源的 MIME 类型
     */
    @NotBlank(message = "【资源的 MIME 类型】不能为空")
    private String mimeType;

    /**
     *  是否启用[0:否，1:是]
     */
    @NotBlank(message = "【是否启用[0:否，1:是]】不能为空")
    private Integer status;

    /**
     *  存储平台
     */
    @NotBlank(message = "【存储平台】不能为空")
    private String storeType;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}