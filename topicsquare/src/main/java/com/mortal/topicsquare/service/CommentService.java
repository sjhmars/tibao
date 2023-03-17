package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.CommentPojo;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.vo.CommentUserVo;
import com.mortal.topicsquare.vo.CommentVo;

public interface CommentService extends IService<CommentPojo> {
    IPage<CommentUserVo> getCommentByArticleId(CommentVo commentVo);
    R saveCommentAll(CommentPojo commentMessage,Integer userId);
    R saveReplayAll(ReplayPojo replayPojo,Integer userId);
}
