package com.moratl.questionbank.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.moratl.questionbank.pojo.AlreadyAnswerPojo;
import com.moratl.questionbank.services.UserAnswerService;
import com.moratl.questionbank.vo.OperateDto;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operate")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @PostMapping("/addResolution")
    public R addResolution(@RequestBody OperateDto operateDto){
        return userAnswerService.addResolution(operateDto);
    }

    @PostMapping("/doAnswer")
    public R doAnswer(@RequestBody OperateDto operateDto){
        return userAnswerService.doAnswer(operateDto);
    }

    @PostMapping("/Accuracy")
    public R accuracy(){
        return userAnswerService.accuracy();
    }

    @PostMapping("/notDo")
    public R notDo(@RequestBody OperateDto operateDto){
        return userAnswerService.notDo(operateDto);
    }

    @PostMapping("/doWrong")
    private R doWrong(@RequestBody OperateDto operateDto){
        return userAnswerService.doWrong(operateDto);
    }

    @PostMapping("/doRight")
    private R doRight(@RequestBody OperateDto operateDto){
        return userAnswerService.doRight(operateDto);
    }

    @PostMapping("/isHasDo")
    private R isHasDo(){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        if (userAnswerService.getMap(new LambdaQueryWrapper<AlreadyAnswerPojo>().eq(AlreadyAnswerPojo::getUserId,userId)) != null){
            return R.ok(true);
        }
        return R.ok(false);
    }

    @PostMapping("/clean")
    public R clean(){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        userAnswerService.remove(new LambdaUpdateWrapper<AlreadyAnswerPojo>().eq(AlreadyAnswerPojo::getUserId,userId));
        return R.ok("清除成功");
    }

    @PostMapping("allAnsweNum")
    public R allAnsweNum(){
        return R.ok(userAnswerService.allansweNum());
    }
}
