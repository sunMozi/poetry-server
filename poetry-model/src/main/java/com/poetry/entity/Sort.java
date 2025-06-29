package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 分类
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("sort")
public class Sort implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  分类名称
     */
    @NotBlank(message = "【分类名称】不能为空")
    private String sortName;

    /**
     *  分类描述
     */
    @NotBlank(message = "【分类描述】不能为空")
    private String sortDescription;

    /**
     *  分类类型[0:导航栏分类，1:普通分类]
     */
    @NotBlank(message = "【分类类型[0:导航栏分类，1:普通分类]】不能为空")
    private Integer sortType;

    /**
     *  分类优先级：数字小的在前面
     */
    @NotBlank(message = "【分类优先级：数字小的在前面】不能为空")
    private Integer priority;

}