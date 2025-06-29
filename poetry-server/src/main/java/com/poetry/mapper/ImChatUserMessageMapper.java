package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.ImChatUserMessage;
import org.apache.ibatis.annotations.Mapper;
/**
 * 单聊记录
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface ImChatUserMessageMapper extends BaseMapper<ImChatUserMessage> {

}