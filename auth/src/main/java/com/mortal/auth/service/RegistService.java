package com.mortal.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.vo.UserRegistVo;
import com.mortal.common.utils.R;

public interface RegistService extends IService<UserPojo> {
    Integer regis(UserRegistVo userRegistVo);
}
