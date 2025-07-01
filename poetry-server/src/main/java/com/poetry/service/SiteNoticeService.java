package com.poetry.service;

import com.poetry.entity.SiteNotice;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.poetry.vo.SiteNoticeVO;
import java.util.List;
/**
 * @author Zyan
 * @since 2025-07-01 20:56:44
 */
public interface SiteNoticeService extends IService<SiteNotice> {

  List<SiteNoticeVO> getLatestActiveNotice();
}