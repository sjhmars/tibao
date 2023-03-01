package com.mortal.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.vo.UserLoginVo;

public interface LoginService extends IService<UserPojo> {
    String loginIng(UserLoginVo userLoginVo);
}
