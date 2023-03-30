package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/square")
public class UserSpaceController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public R getUserMessage(@RequestBody UserVo userVo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        System.out.println(userVo.getUserId());
        UserPojo userMessage = userService.getUserMessage(userId,userVo);
        return R.ok(userMessage);
    }

    //获取用户所有文章
    @PostMapping("/getUserArticle")
    public R getUserArticle(@RequestBody ArticleVo articleVo) {
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

    @PostMapping("/updateIntro")
    public R updateIntro(@RequestBody UserPojo userPojo){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        if (userService.update(new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getId,userId).set(UserPojo::getUserIntro,userPojo.getUserIntro()))){
            return R.ok("修改成功");
        }
        return R.failed("修改失败");
    }

    //public R getUserArticle(@RequestBody UserPojo)
}
