package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.SwiperPojo;

public interface SwiperService extends IService<SwiperPojo> {
    R getAll();
}
