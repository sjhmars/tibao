package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.topicsquare.pojo.NoticePojo;
import com.mortal.topicsquare.vo.NoticeVo;

public interface NoticeService extends IService<NoticePojo> {
    IPage<NoticePojo> getAllNoticeById(NoticeVo noticeVo);
}
