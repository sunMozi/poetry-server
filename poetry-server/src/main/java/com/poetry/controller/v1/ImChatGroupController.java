package com.poetry.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poetry.service.ImChatGroupService;

/**
 * 聊天群
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/imChatGroup")
public class ImChatGroupController {

    @Autowired
    private ImChatGroupService imChatGroupService;


}