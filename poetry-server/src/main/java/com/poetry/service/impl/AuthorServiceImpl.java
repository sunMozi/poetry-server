package com.poetry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.Author;
import com.poetry.mapper.AuthorMapper;
import com.poetry.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zyan
 * @since 2025-07-02 11:52:04
 */
@Service
@Slf4j
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

  @Autowired
  private AuthorMapper authorMapper;

}