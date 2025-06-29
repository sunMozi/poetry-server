package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 网站信息表
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("web_info")
public class WebInfo implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  网站名称
     */
    @NotBlank(message = "【网站名称】不能为空")
    private String webName;

    /**
     *  网站信息
     */
    @NotBlank(message = "【网站信息】不能为空")
    private String webTitle;

    /**
     *  公告
     */
    @NotBlank(message = "【公告】不能为空")
    private String notices;

    /**
     *  页脚
     */
    @NotBlank(message = "【页脚】不能为空")
    private String footer;

    /**
     *  背景
     */
    @NotBlank(message = "【背景】不能为空")
    private String backgroundImage;

    /**
     *  头像
     */
    @NotBlank(message = "【头像】不能为空")
    private String avatar;

    /**
     *  随机头像
     */
    @NotBlank(message = "【随机头像】不能为空")
    private String randomAvatar;

    /**
     *  随机名称
     */
    @NotBlank(message = "【随机名称】不能为空")
    private String randomName;

    /**
     *  随机封面
     */
    @NotBlank(message = "【随机封面】不能为空")
    private String randomCover;

    /**
     *  看板娘消息
     */
    @NotBlank(message = "【看板娘消息】不能为空")
    private String waifuJson;

    /**
     *  是否启用[0:否，1:是]
     */
    @NotBlank(message = "【是否启用[0:否，1:是]】不能为空")
    private Integer status;

}