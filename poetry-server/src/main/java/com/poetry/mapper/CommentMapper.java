package com.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetry.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
/**
 * 文章评论表
 *
 * @author system
 * @since 2025-06-29 11:49:33
 *
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}