package com.poetry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.poetry.entity.WebInfo;
import com.poetry.mapper.WebInfoMapper;
import com.poetry.service.WebInfoService;
import com.poetry.vo.WebInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author system
 * @since 2025-06-29 11:49:33
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WebInfoServiceImpl extends ServiceImpl<WebInfoMapper, WebInfo> implements
    WebInfoService {

  private final WebInfoMapper webInfoMapper;

  @Override
  public WebInfoVO getWebInfo() {
    WebInfo webInfo = webInfoMapper.selectById(1);
    WebInfoVO webInfoVO = new WebInfoVO();
    BeanUtil.copyProperties(webInfo, webInfoVO);

    // TODO: 从缓存或统计服务中获取历史总访问数并赋值

    // TODO: 从缓存或统计服务中获取历史日访问数并赋值

    // TODO: 如需根据配置动态生成默认存储类型，此处应补充逻辑

    return webInfoVO;
  }
}