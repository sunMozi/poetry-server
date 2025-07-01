package com.poetry.controller.v1;


import com.poetry.SiteNoticeControllerApi;
import com.poetry.common.response.ResponseResult;
import com.poetry.service.SiteNoticeService;
import com.poetry.vo.SiteNoticeVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/1 21:07
 * @description
 */
@RestController
@RequiredArgsConstructor
public class SiteNoticeController implements SiteNoticeControllerApi {

  private final SiteNoticeService siteNoticeService;


  @Override
  public ResponseResult<List<SiteNoticeVO>> getLatestActiveNotice() {
    return ResponseResult.ok(siteNoticeService.getLatestActiveNotice());
  }
}