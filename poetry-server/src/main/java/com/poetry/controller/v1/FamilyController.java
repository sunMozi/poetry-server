package com.poetry.controller.v1;

import com.poetry.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 家庭信息
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/family")
public class FamilyController {

  @Autowired
  private FamilyService familyService;


}