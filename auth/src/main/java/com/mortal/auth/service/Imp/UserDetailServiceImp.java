package com.mortal.auth.service.Imp;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.auth.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPojo userPojo = userMapper.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,username));
        LoginUser loginUser = new LoginUser();
        if (ObjectUtil.isEmpty(userPojo)){
            throw new RuntimeException("用户不存在");
        }
        if (userPojo.getIsDelete()==1){
            loginUser.set_delete(false);
        }
        loginUser.setUserPojo(userPojo);
        String permission = userPojo.getUserType();
        List<String> permissions = Stream.of(permission.split(":")).collect(Collectors.toList());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
