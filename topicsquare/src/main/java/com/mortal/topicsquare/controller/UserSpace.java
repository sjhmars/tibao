package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import com.mortal.topicsquare.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/square")
public class UserSpace {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public R getUserMessage(@RequestBody UserVo userVo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        String JobNumber = loginUser.getUserPojo().getJobNumber();
        UserPojo userMessage;
            //获取自己
        if (userVo.getJobNumber().equals(JobNumber)){
            userMessage = userService.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userVo.getJobNumber()));
            userMessage.setUserPassword(null);
        } else {
            userMessage = userService.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userVo.getJobNumber()));
            //获取他人
            userMessage.setIsDelete(null);
            userMessage.setUserPassword(null);
            userMessage.setStatus(null);
        }
        return R.ok(userMessage);
    }

    @PostMapping("/changeUserMessage")
    @Transactional
    public R changeUserMessage(@RequestBody UserPojo userMessage) {
        userService.update(userMessage,new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userMessage.getJobNumber()));
        return R.ok();
    }
}
