package com.poetry;


import com.poetry.common.response.ResponseResult;
import com.poetry.vo.SiteNoticeVO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/7/1 20:58
 * @description
 */
@RestController
@RequestMapping("/siteNotice")
public interface SiteNoticeControllerApi {


  @GetMapping("/latest")
  ResponseResult<List<SiteNoticeVO>> getLatestActiveNotice();


}