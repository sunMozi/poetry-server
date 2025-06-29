package com.poetry.controller;

import com.poetry.service.HistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 历史信息
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/historyInfo")
public class HistoryInfoController {

  @Autowired
  private HistoryInfoService historyInfoService;


}