package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.topicsquare.pojo.CollegePojo;

public interface CollegeService extends IService<CollegePojo> {
    CollegePojo getAllCollege();
}
