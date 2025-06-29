package com.poetry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.Comment;
import com.poetry.mapper.CommentMapper;
import com.poetry.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author system
 * @since 2025-06-29 11:49:33
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements
    CommentService {

  @Autowired
  private CommentMapper commentMapper;

}