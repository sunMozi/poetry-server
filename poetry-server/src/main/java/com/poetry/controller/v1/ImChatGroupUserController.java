package com.poetry.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poetry.service.ImChatGroupUserService;

/**
 * 聊天群成员
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/imChatGroupUser")
public class ImChatGroupUserController {

    @Autowired
    private ImChatGroupUserService imChatGroupUserService;


}