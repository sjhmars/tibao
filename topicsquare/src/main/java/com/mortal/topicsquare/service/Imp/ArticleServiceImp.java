package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.mapper.ArticleMapper;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.*;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.LikeArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImp extends ServiceImpl<ArticleMapper, ArticlePojo> implements ArticleService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplayService replayService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public R deleteArticle(ArticlePojo articlePojo,Integer userId) {
        this.remove(new LambdaQueryWrapper<ArticlePojo>().eq(ArticlePojo::getUserId,userId).eq(ArticlePojo::getArticleId,articlePojo.getArticleId()));

        CommentPojo commentMessage = new CommentPojo();
        //commentMessage.setArticleMessageId(articlePojo.getArticleId());
        List<CommentPojo> list = commentService.list(new LambdaQueryWrapper<CommentPojo>().eq(CommentPojo::getArticleMessageId,articlePojo.getArticleId()));

        //ReplayPojo replayMessage = new ReplayPojo();

        for (CommentPojo commentPojo : list) {
            replayService.remove(new LambdaQueryWrapper<ReplayPojo>().eq(ReplayPojo::getCommentId,commentPojo.getId()));
        }
        //commentService.delete(commentMessage);
        commentService.removeById(commentMessage.getId());

//        NoticePojo noticeMessage = new NoticePojo();
//        noticeMessage.setArticleId(articlePojo.getArticleId());
        noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getArticleId,articlePojo.getArticleId()));
        return R.ok("删除成功");
    }

    @Override
    public R deleteReplay(ReplayPojo replayPojo,Integer userId) {

        ReplayPojo replayMessage = new ReplayPojo();
        replayMessage.setReplayId(replayPojo.getReplayId());
        replayMessage.setUserId(userId);
        replayService.removeById(replayPojo.getReplayId());

        NoticePojo noticeMessage = new NoticePojo();
        noticeMessage.setReplayId(replayPojo.getReplayId());
        noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getReplayId,replayPojo.getReplayId()));
        return null;
    }

    @Override
    public R getLikeArticle(ArticlePojo articlePojo, Integer userId) {
        LikePojo likeMessage = new LikePojo();
        likeMessage.setArticleId(articlePojo.getArticleId());
        likeMessage.setUserId(userId);

        LikePojo likePojo = likeService.getOne(new LambdaQueryWrapper<LikePojo>().eq(LikePojo::getArticleId,articlePojo.getArticleId()).eq(LikePojo::getUserId,userId));
        if (likePojo == null) {
            return R.ok(false);
        }
        return R.ok(true);
    }

    @Override
    /**
     * 收藏文章
     */
    public R likeArticle(ArticlePojo articlePojo, Integer userId) {
        LikePojo likePojo = new LikePojo();
        likePojo.setCreateTime(new Date());
        likePojo.setArticleId(articlePojo.getArticleId());
        likePojo.setUserId(userId);
        LikePojo likePojo1 = likeService.getOne(new LambdaQueryWrapper<LikePojo>().eq(LikePojo::getArticleId,articlePojo.getArticleId()).eq(LikePojo::getUserId,userId));
        if (likePojo1 == null) {
            likeService.save(likePojo);
        } else {
            likeService.remove(new LambdaQueryWrapper<LikePojo>().eq(LikePojo::getArticleId,articlePojo.getArticleId()));
        }

        return R.ok();
    }

    @Override
    public IPage<ArticleUserVo> getArticle(ArticleVo articleVo) {
        Page<ArticleUserVo> page1 = new Page<>(articleVo.getPageNumber(),10);
        IPage<ArticleUserVo> page = articleMapper.selectAllArticle(page1,null,null,null);
        return page;
    }

    @Override
    public ArticleUserVo getArticleById(Integer articleId) {
        return articleMapper.selectByArticleId(articleId);
    }

    @Override
    public IPage<ArticleUserVo> getArticleByCollegeId(ArticleVo articleVo) {
        Page<ArticleUserVo> page = new Page<>(articleVo.getPageNumber(),10);
        return articleMapper.selectAllArticle(page,null,articleVo.getCollegeId(),null);
    }

    @Override
    public IPage<LikeArticleVo> getAllLikeArticle(Integer pageNumber,Integer userId) {
        Page<LikeArticleVo> page = new Page<>(pageNumber,10);
        return articleMapper.selectByArticleIdLikeArticleVo(page,userId);
    }
}

