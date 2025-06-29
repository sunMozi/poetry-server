package com.poetry.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poetry.service.WebInfoService;

/**
 * 网站信息表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/webInfo")
public class WebInfoController {

    @Autowired
    private WebInfoService webInfoService;


}