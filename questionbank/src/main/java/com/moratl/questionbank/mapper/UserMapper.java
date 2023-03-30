package com.moratl.questionbank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moratl.questionbank.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPojo> {
}
