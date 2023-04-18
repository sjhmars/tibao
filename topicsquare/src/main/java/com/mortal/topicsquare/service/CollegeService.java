package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.topicsquare.pojo.CollegePojo;

import java.util.List;

public interface CollegeService extends IService<CollegePojo> {
    List<CollegePojo> getAllCollege();
    boolean click(CollegePojo collegePojo);
}
