package com.mortal.topicsquare.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.ArticlePojo;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.LikeArticleVo;

import java.util.List;

public interface ArticleService extends IService<ArticlePojo> {
    R deleteArticle(ArticlePojo articlePojo,Integer userId);
    R deleteReplay(ReplayPojo replayPojo,Integer userId);
    R getLikeArticle(ArticlePojo articlePojo,Integer userId);
    R likeArticle(ArticlePojo articlePojo,Integer userId);
    IPage<ArticleUserVo> getArticle(ArticleVo articleVo);
    ArticleUserVo getArticleById(Integer articleId);
    IPage<ArticleUserVo> getArticleByCollegeId(ArticleVo articleVo);
    IPage<LikeArticleVo> getAllLikeArticle(Integer pageNumber,Integer userId);
    R saveToqb(ArticlePojo articlePojo);
    IPage<ArticleUserVo> getArticleByName(ArticleVo articleVo);
}
