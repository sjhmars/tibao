package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.SwiperPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwiperMapper extends BaseMapper<SwiperPojo> {
    List<SwiperPojo> selectAll();
}
