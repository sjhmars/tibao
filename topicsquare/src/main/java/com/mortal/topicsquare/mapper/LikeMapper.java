package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.LikePojo;
import com.mortal.topicsquare.vo.LikeUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper extends BaseMapper<LikePojo> {
    LikeUserVo getByLikeId(@Param("likeId") Integer likeId);
}
