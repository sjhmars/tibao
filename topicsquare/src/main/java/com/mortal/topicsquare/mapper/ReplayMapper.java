package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.vo.ReplayContentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplayMapper extends BaseMapper<ReplayPojo> {
    List<ReplayContentVo> searchAllByCommentId(@Param("commentId") Integer commentId);
}
