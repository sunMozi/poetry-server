package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 聊天群成员
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("im_chat_group_user")
public class ImChatGroupUser implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Integer id;

    /**
     *  群ID
     */
    @NotBlank(message = "【群ID】不能为空")
    private Integer groupId;

    /**
     *  用户ID
     */
    @NotBlank(message = "【用户ID】不能为空")
    private Integer userId;

    /**
     *  审核用户ID
     */
    @NotBlank(message = "【审核用户ID】不能为空")
    private Integer verifyUserId;

    /**
     *  备注
     */
    @NotBlank(message = "【备注】不能为空")
    private String remark;

    /**
     *  是否管理员[0:否，1:是]
     */
    @NotBlank(message = "【是否管理员[0:否，1:是]】不能为空")
    private Integer adminFlag;

    /**
     *  用户状态[0:未审核，1:审核通过，2:禁言]
     */
    @NotBlank(message = "【用户状态[0:未审核，1:审核通过，2:禁言]】不能为空")
    private Integer userStatus;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}