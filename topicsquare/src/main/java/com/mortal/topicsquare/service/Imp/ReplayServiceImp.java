package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.ReplayMapper;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.service.ReplayService;
import com.mortal.topicsquare.vo.ReplayContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplayServiceImp extends ServiceImpl<ReplayMapper, ReplayPojo> implements ReplayService {
    @Autowired
    private ReplayMapper replayMapper;

    @Override
    public List<ReplayContentVo> getReplayContent(Integer CommentId) {
        return replayMapper.searchAllByCommentId(CommentId);
    }
}
