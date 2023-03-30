package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.mapper.SwiperMapper;
import com.mortal.topicsquare.pojo.SwiperPojo;
import com.mortal.topicsquare.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiperServiceImp extends ServiceImpl<SwiperMapper,SwiperPojo> implements SwiperService {

    @Autowired
    private SwiperMapper swiperMapper;

    @Override
    public R getAll() {
        return R.ok(swiperMapper.selectAll());
    }
}
