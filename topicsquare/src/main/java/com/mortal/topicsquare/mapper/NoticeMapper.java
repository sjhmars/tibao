package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.NoticePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<NoticePojo> {
}
