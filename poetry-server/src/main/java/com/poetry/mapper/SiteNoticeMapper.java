package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.SiteNotice;
import org.apache.ibatis.annotations.Mapper;
/**
 * 网站主页公告通知表
 *
 * @author Zyan
 * @since 2025-07-01 20:56:44
 *
 */
@Mapper
public interface SiteNoticeMapper extends BaseMapper<SiteNotice> {

}