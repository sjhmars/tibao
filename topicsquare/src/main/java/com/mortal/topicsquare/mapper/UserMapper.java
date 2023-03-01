package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPojo> {
}
