package com.poetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.poetry.entity.WebInfo;
import com.poetry.service.WebInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

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