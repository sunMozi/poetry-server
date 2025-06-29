package com.poetry.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poetry.service.ImChatUserGroupMessageService;

/**
 * 群聊记录
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/imChatUserGroupMessage")
public class ImChatUserGroupMessageController {

    @Autowired
    private ImChatUserGroupMessageService imChatUserGroupMessageService;


}