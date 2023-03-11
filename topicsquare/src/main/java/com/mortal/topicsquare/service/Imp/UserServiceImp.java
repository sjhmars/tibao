package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.mapper.UserMapper;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import com.mortal.topicsquare.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserPojo> implements UserService {

    @Override
    public UserPojo getUserMessage(String jobNumber, UserVo userVo) {
        UserPojo userMessage;
        //获取自己
        if (userVo.getJobNumber().equals(jobNumber)){
            userMessage = this.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userVo.getJobNumber()));
            userMessage.setUserPassword(null);
        } else {
            userMessage = this.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userVo.getJobNumber()));
            //获取他人
            userMessage.setIsDelete(null);
            userMessage.setUserPassword(null);
            userMessage.setStatus(null);
        }
        return userMessage;
    }
}
