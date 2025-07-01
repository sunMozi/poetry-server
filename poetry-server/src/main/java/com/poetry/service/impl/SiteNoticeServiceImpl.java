package com.poetry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.SiteNotice;
import com.poetry.mapper.SiteNoticeMapper;
import com.poetry.service.SiteNoticeService;
import com.poetry.vo.SiteNoticeVO;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Zyan
 * @since 2025-07-01 20:56:44
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SiteNoticeServiceImpl extends ServiceImpl<SiteNoticeMapper, SiteNotice> implements
    SiteNoticeService {

  private final SiteNoticeMapper siteNoticeMapper;

  @Override
  public List<SiteNoticeVO> getLatestActiveNotice() {
    List<SiteNotice> siteNotices = siteNoticeMapper.selectList(new LambdaQueryWrapper<SiteNotice>().eq(
                                                                                                       SiteNotice::getIsActive,
                                                                                                       true)
                                                                                                   .le(SiteNotice::getStartTime,
                                                                                                       LocalDateTime.now())
                                                                                                   .and(
                                                                                                       wrapper -> wrapper.isNull(
                                                                                                                             SiteNotice::getEndTime)
                                                                                                                         .or()
                                                                                                                         .ge(SiteNotice::getEndTime,
                                                                                                                             LocalDateTime.now()))
                                                                                                   .orderByDesc(
                                                                                                       SiteNotice::getStartTime)
                                                                                                   .last(
                                                                                                       "LIMIT 5"));

    if (siteNotices == null || siteNotices.isEmpty()) {
      log.info("未查询到有效公告");
      return Collections.emptyList();
    }

    return siteNotices.stream().map(siteNotice -> {
      SiteNoticeVO vo = new SiteNoticeVO();
      BeanUtil.copyProperties(siteNotice, vo);
      return vo;
    }).collect(Collectors.toList());
  }

}