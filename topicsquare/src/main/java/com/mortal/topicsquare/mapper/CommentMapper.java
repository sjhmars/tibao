package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mortal.topicsquare.pojo.CommentPojo;
import com.mortal.topicsquare.vo.CommentUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper extends BaseMapper<CommentPojo> {
    IPage<CommentUserVo> selectComment(IPage<CommentUserVo> page, @Param("articleId") Integer articleId);
    CommentUserVo selectCommentUserVo(@Param("commentId") Integer commentId);
}
