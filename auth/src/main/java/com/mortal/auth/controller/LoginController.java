package com.mortal.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.service.Imp.LoginServiceImp;
import com.mortal.auth.service.LoginService;
import com.mortal.auth.service.RegistService;
import com.mortal.auth.vo.UserLoginVo;
import com.mortal.auth.vo.UserRegistVo;
import com.mortal.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @RefreshScope 动态刷新配置文件
 * **/
@RefreshScope
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    RegistService registService;

    @Autowired
    LoginService loginService;

//    private String name;
//
//    private Integer age;
//    @RequestMapping("/test")
//    public R test(){
//        HashMap<String,Integer> map = new HashMap<>();
//        map.put(name,age);
//        return R.ok(map);
//    }


    /**
     * 结合security和JWT+redis实现单点登录,原本想结合gateway网关，但是怕消耗网关性能
     * **/
    @PostMapping("/login")
    public R login(@Valid @RequestBody UserLoginVo userLoginVo, BindingResult result){
        return loginService.login(userLoginVo);
    }

    /**
     * 旧方式登录
     * **/
    @PostMapping("/login2")
    public R Login2(@Valid @RequestBody UserLoginVo userLoginVo, BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
            return R.failed(errors);
        }
        String msg = loginService.loginIng(userLoginVo);
        if (StrUtil.isNotBlank(msg)){
            if (msg.contains("成功")){
                return R.ok(msg);
            }else if (msg.contains("错误"))
                return R.failed(msg);
        }
        return R.failed("用户不存在");
    }


    @PostMapping("/regist")
    public R regist(@Valid @RequestBody UserRegistVo userRegistVo, BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
            return R.failed(errors);
        }
        int i = registService.regis(userRegistVo);
        if (i == 2){
            return R.failed("工号重复");
        }else if (i > 0){
            return  R.ok("注册成功");
        }
        return R.failed("注册失败");
    }

    //退出登录
    @PostMapping("/loginOut")
    public R loginOut(){
        return loginService.loginOut();
    }

    //注销
    @PostMapping("/logOff")
    public R logOff(@Valid @RequestBody UserLoginVo userLoginVo,BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            return R.failed(errors);
        }
        return loginService.logOff(userLoginVo);
    }

}
