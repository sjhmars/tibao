package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.LikeMapper;
import com.mortal.topicsquare.pojo.LikePojo;
import com.mortal.topicsquare.service.LikeService;
import com.mortal.topicsquare.vo.LikeUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImp extends ServiceImpl<LikeMapper, LikePojo> implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public LikeUserVo getLikeById(Integer likeId) {
        return likeMapper.getByLikeId(likeId);
    }
}
