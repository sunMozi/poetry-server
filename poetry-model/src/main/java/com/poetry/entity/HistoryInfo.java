package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 历史信息
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("history_info")
public class HistoryInfo implements Serializable{

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
     *  IP
     */
    @NotBlank(message = "【IP】不能为空")
    private String ip;

    /**
     *  国家
     */
    @NotBlank(message = "【国家】不能为空")
    private String nation;

    /**
     *  省份
     */
    @NotBlank(message = "【省份】不能为空")
    private String province;

    /**
     *  城市
     */
    @NotBlank(message = "【城市】不能为空")
    private String city;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}