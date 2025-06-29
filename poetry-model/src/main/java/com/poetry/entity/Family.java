package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 家庭信息
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("family")
public class Family implements Serializable{

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
     *  背景封面
     */
    @NotBlank(message = "【背景封面】不能为空")
    private String bgCover;

    /**
     *  男生头像
     */
    @NotBlank(message = "【男生头像】不能为空")
    private String manCover;

    /**
     *  女生头像
     */
    @NotBlank(message = "【女生头像】不能为空")
    private String womanCover;

    /**
     *  男生昵称
     */
    @NotBlank(message = "【男生昵称】不能为空")
    private String manName;

    /**
     *  女生昵称
     */
    @NotBlank(message = "【女生昵称】不能为空")
    private String womanName;

    /**
     *  计时
     */
    @NotBlank(message = "【计时】不能为空")
    private String timing;

    /**
     *  倒计时标题
     */
    @NotBlank(message = "【倒计时标题】不能为空")
    private String countdownTitle;

    /**
     *  倒计时时间
     */
    @NotBlank(message = "【倒计时时间】不能为空")
    private String countdownTime;

    /**
     *  是否启用[0:否，1:是]
     */
    @NotBlank(message = "【是否启用[0:否，1:是]】不能为空")
    private Integer status;

    /**
     *  额外信息
     */
    @NotBlank(message = "【额外信息】不能为空")
    private String familyInfo;

    /**
     *  点赞数
     */
    @NotBlank(message = "【点赞数】不能为空")
    private Integer likeCount;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

    /**
     *  最终修改时间
     */
    @NotBlank(message = "【最终修改时间】不能为空")
    private Date updateTime;

}