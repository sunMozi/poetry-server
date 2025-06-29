package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 参数配置表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  名称
     */
    @NotBlank(message = "【名称】不能为空")
    private String configName;

    /**
     *  键名
     */
    @NotBlank(message = "【键名】不能为空")
    private String configKey;

    /**
     *  键值
     */
    @NotBlank(message = "【键值】不能为空")
    private String configValue;

    /**
     *  1 私用 2 公开
     */
    @NotBlank(message = "【1 私用 2 公开】不能为空")
    private String configType;

}