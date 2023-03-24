package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.mapper.ArticleMapper;
import com.mortal.topicsquare.mapper.UserMapper;
import com.mortal.topicsquare.pojo.UserPojo;
import com.mortal.topicsquare.service.UserService;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserPojo> implements UserService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public UserPojo getUserMessage(Integer userId, UserVo userVo) {
        UserPojo userMessage;
        //获取自己
        if (userVo.getUserId().equals(userId)){
            userMessage = this.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getId,userVo.getUserId()));
            userMessage.setUserPassword(null);
        } else {
            userMessage = this.getOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getId,userVo.getUserId()));
            //获取他人
            userMessage.setIsDelete(null);
            userMessage.setUserPassword(null);
            userMessage.setStatus(null);
        }
        return userMessage;
    }

    @Override
    public IPage<ArticleUserVo> getNewArticle(ArticleVo articleVo) {
        Page<ArticleUserVo> page = new Page<>(articleVo.getPageNumber(),10);
        return articleMapper.selectAllArticle(page,null,null,articleVo.getUserId());
    }
}
