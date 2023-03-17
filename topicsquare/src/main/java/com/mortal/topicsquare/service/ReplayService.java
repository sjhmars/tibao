package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.vo.ReplayContentVo;

import java.util.List;

public interface ReplayService extends IService<ReplayPojo> {
     List<ReplayContentVo> getReplayContent(Integer CommentId);
     ReplayContentVo getReplayContentById(Integer replayId);
}
