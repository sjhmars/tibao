package com.mortal.auth.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.service.LoginService;
import com.mortal.auth.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImp extends ServiceImpl<UserMapper, UserPojo> implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String loginIng(UserLoginVo userLoginVo) {
        List<UserPojo> result = userMapper.selectList(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getPhoneNumber,userLoginVo.getPhoneNumber()));
        if (result.size()==0){
            return "";
        }else{
            for (UserPojo userPojo : result) {
                String password = userPojo.getUserPassword();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                boolean  judge = encoder.matches(userLoginVo.getUserPassword(),password);
                if (judge){
                    return "登录成功";
                }
            }
        }
        return "密码错误";
    }
}
