package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.topicsquare.pojo.LikePojo;
import com.mortal.topicsquare.vo.LikeUserVo;

public interface LikeService extends IService<LikePojo> {
    LikeUserVo getLikeById(Integer likeId);
}
