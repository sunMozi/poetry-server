package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 标签
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("label")
public class Label implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  分类ID
     */
    @NotBlank(message = "【分类ID】不能为空")
    private Integer sortId;

    /**
     *  标签名称
     */
    @NotBlank(message = "【标签名称】不能为空")
    private String labelName;

    /**
     *  标签描述
     */
    @NotBlank(message = "【标签描述】不能为空")
    private String labelDescription;

}