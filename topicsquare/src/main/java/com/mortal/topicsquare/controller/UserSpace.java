package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/square")
public class UserSpace {

    private UserService userService;

    @PostMapping("/getUser")
    public R getUserMessage(@RequestBody UserPojo userPojo) {
        int i = 1;
        UserPojo userMessage = null;
            //获取自己
        userMessage = userService.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userPojo.getJobNumber()));
        if (i == 1){

        } else {
            userMessage = userService.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userPojo.getJobNumber()));
            //获取他人
            userMessage.setIsDelete(null);
            userMessage.setUserPassword(null);
            userMessage.setStatus(null);
        }
        return R.ok(userMessage);
    }
}
