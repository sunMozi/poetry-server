package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 聊天群
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("im_chat_group")
public class ImChatGroup implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  群名称
     */
    @NotBlank(message = "【群名称】不能为空")
    private String groupName;

    /**
     *  群主用户ID
     */
    @NotBlank(message = "【群主用户ID】不能为空")
    private Integer masterUserId;

    /**
     *  群头像
     */
    @NotBlank(message = "【群头像】不能为空")
    private String avatar;

    /**
     *  简介
     */
    @NotBlank(message = "【简介】不能为空")
    private String introduction;

    /**
     *  公告
     */
    @NotBlank(message = "【公告】不能为空")
    private String notice;

    /**
     *  进入方式[0:无需验证，1:需要群主或管理员同意]
     */
    @NotBlank(message = "【进入方式[0:无需验证，1:需要群主或管理员同意]】不能为空")
    private Integer inType;

    /**
     *  类型[1:聊天群，2:话题]
     */
    @NotBlank(message = "【类型[1:聊天群，2:话题]】不能为空")
    private Integer groupType;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}