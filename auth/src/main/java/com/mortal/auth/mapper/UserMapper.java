package com.mortal.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.auth.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserPojo> {
}
