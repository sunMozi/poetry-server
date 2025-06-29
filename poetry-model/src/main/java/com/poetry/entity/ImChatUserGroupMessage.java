package com.poetry.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 群聊记录
 *
 * @author system
 * @since 2025-06-29 11:44:05
 */
@Data
@TableName("im_chat_user_group_message")
public class ImChatUserGroupMessage implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  ID
     */
    @NotBlank(message = "【ID】不能为空")
    private Long id;

    /**
     *  群ID
     */
    @NotBlank(message = "【群ID】不能为空")
    private Integer groupId;

    /**
     *  发送ID
     */
    @NotBlank(message = "【发送ID】不能为空")
    private Integer fromId;

    /**
     *  接收ID
     */
    @NotBlank(message = "【接收ID】不能为空")
    private Integer toId;

    /**
     *  内容
     */
    @NotBlank(message = "【内容】不能为空")
    private String content;

    /**
     *  创建时间
     */
    @NotBlank(message = "【创建时间】不能为空")
    private Date createTime;

}