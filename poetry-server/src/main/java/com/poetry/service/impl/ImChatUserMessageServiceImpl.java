package com.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poetry.mapper.ImChatUserMessageMapper;
import com.poetry.entity.ImChatUserMessage;
import com.poetry.service.ImChatUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @author system
 * @since 2025-06-29 11:49:33
 */
@Service
@Slf4j
public class ImChatUserMessageServiceImpl extends ServiceImpl<ImChatUserMessageMapper, ImChatUserMessage> implements ImChatUserMessageService {

    @Autowired
    private ImChatUserMessageMapper imChatUserMessageMapper;

}