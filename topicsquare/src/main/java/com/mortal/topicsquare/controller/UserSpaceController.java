package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/square")
public class UserSpaceController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public R getUserMessage(@RequestBody UserVo userVo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        String jobNumber = loginUser.getUserPojo().getJobNumber();

        UserPojo userMessage = userService.getUserMessage(jobNumber,userVo);
        return R.ok(userMessage);
    }

    @PostMapping("/changeUserMessage")
    public R changeUserMessage(@RequestBody UserPojo userMessage) {
        userService.update(userMessage,new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getJobNumber,userMessage.getJobNumber()));
        return R.ok();
    }

    //获取用户所有文章
    @PostMapping("/getUserArticle")
    public R getUserArticle(@RequestBody ArticleVo articleVo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        articleVo.setUserId(userId);
        return R.ok(userService.getNewArticle(articleVo));
    }

    //获取用户id
    @GetMapping("/getUserId")
    public R getUserId() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        return R.ok(userId);
    }

    //public R getUserArticle(@RequestBody UserPojo)
}
