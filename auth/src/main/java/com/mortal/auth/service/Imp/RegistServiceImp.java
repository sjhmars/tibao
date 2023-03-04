package com.mortal.auth.service.Imp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.mapper.UserMapper;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.service.RegistService;
import com.mortal.auth.vo.UserRegistVo;
import com.mortal.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistServiceImp extends ServiceImpl<UserMapper, UserPojo> implements RegistService {

    @Override
    public Integer regis(UserRegistVo userRegistVo) {
        UserPojo userPojo = new UserPojo();
        userPojo.setUserName(userRegistVo.getUserName());
        userPojo.setIsDelete(0);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncoder =  bCryptPasswordEncoder.encode(userRegistVo.getUserPassword());
        userPojo.setUserPassword(passwordEncoder);
        userPojo.setSex(userRegistVo.getSex());
        userPojo.setPhoneNumber(userRegistVo.getPhoneNumber());
        userPojo.setUserType(userRegistVo.getUserType());
        userPojo.setJobNumber(userRegistVo.getJobNumber());
        try {
            if (baseMapper.insert(userPojo)>0){
                return userPojo.getId();
            }
        }catch (Exception e){
            String errMsg = e.getMessage();
            if (StrUtil.isNotBlank(errMsg)&&errMsg.contains("user.one")){
                return 2;
            }
        }
        return 0;
    }
}
