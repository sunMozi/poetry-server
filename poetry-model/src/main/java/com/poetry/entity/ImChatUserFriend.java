package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 好友
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("im_chat_user_friend")
public class ImChatUserFriend implements Serializable{

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
     *  好友ID
     */
    @NotBlank(message = "【好友ID】不能为空")
    private Integer friendId;

    /**
     *  朋友状态[0:未审核，1:审核通过]
     */
    @NotBlank(message = "【朋友状态[0:未审核，1:审核通过]】不能为空")
    private Integer friendStatus;

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