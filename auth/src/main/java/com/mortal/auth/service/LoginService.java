package com.mortal.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.auth.pojo.UserPojo;
import com.mortal.auth.vo.UserLoginVo;
import com.mortal.auth.vo.UserRegistVo;
import com.mortal.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface LoginService extends IService<UserPojo> {
    String loginIng(UserLoginVo userLoginVo);
    R login(UserLoginVo userLoginVo);
    R loginOut();
    R logOff(UserLoginVo userLoginVo);
    R changeUser(UserRegistVo userRegistVo);
    R getUserAdmin();
    R findPassword(UserRegistVo userRegistVo);
    R uploadImage(MultipartFile file);
}
