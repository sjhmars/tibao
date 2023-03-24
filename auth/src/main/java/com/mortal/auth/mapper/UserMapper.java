package com.mortal.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserPojo> {
    UserVo searchAllById(@Param("userId") Integer userId);
}
