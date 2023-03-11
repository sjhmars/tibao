package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.ReplayMapper;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.service.ReplayService;
import org.springframework.stereotype.Service;

@Service
public class ReplayServiceImp extends ServiceImpl<ReplayMapper, ReplayPojo> implements ReplayService {
}
