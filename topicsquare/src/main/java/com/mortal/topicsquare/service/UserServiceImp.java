package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.UserMapper;
import com.mortal.topicsquare.pojo.UserPojo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserPojo> implements UserService {
}
