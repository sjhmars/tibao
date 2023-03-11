package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.CommentPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<CommentPojo> {
}
