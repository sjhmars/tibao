package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.ArticlePojo;
import com.mortal.topicsquare.pojo.ReplayPojo;

public interface ArticleService extends IService<ArticlePojo> {
    R deleteArticle(ArticlePojo articlePojo,Integer userId);
    R deleteReplay(ReplayPojo replayPojo,Integer userId);
    R getLikeArticle(ArticlePojo articlePojo,Integer userId);
    R likeArticle(ArticlePojo articlePojo,Integer userId);
}
