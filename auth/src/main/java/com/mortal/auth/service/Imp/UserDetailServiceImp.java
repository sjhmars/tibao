package com.mortal.auth.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPojo userPojo = userMapper.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,username));
        return null;
    }
}
