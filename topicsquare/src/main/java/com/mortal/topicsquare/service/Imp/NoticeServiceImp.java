package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.NoticeMapper;
import com.mortal.topicsquare.pojo.NoticePojo;
import com.mortal.topicsquare.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImp extends ServiceImpl<NoticeMapper, NoticePojo> implements NoticeService {
}
