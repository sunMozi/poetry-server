package com.poetry.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poetry.service.ResourcePathService;

/**
 * 资源聚合
 *
 * @author system
 * @since 2025-06-29 11:49:33
 */
@RestController
@RequestMapping("/resourcePath")
public class ResourcePathController {

    @Autowired
    private ResourcePathService resourcePathService;


}