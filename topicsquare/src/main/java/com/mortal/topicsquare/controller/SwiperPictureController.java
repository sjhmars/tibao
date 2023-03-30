package com.mortal.topicsquare.controller;

import com.mortal.common.utils.R;
import com.mortal.topicsquare.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swiper")
public class SwiperPictureController {

    @Autowired
    private SwiperService swiperService;

    @PostMapping("getImage")
    public R getImage(){
        return swiperService.getAll();
    }
}
