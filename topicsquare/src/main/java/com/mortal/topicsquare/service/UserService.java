package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.vo.UserVo;

public interface UserService extends IService<UserPojo> {
    UserPojo getUserMessage(String jobNumber, UserVo userVo);
}
