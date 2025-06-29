package com.poetry.controller.v1;

import com.poetry.service.WeiYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微言表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/weiYan")
public class WeiYanController {

  @Autowired
  private WeiYanService weiYanService;


}