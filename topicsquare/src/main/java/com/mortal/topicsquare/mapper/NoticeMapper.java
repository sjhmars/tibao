package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mortal.topicsquare.pojo.NoticePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper extends BaseMapper<NoticePojo> {
    IPage<NoticePojo> selectAllByUserId(IPage<NoticePojo> page, @Param("userId") Integer userId);
}
