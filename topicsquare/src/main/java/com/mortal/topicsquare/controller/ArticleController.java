package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.enums.CodeEnum;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.ArticleService;
import com.mortal.topicsquare.service.CommentService;
import com.mortal.topicsquare.service.NoticeService;
import com.mortal.topicsquare.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/saveArticle")
    public R saveArticle(@RequestBody ArticlePojo articleMessage) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        articleMessage.setUserId(userId);
        articleMessage.setCreateTime(new Date());
        articleService.save(articleMessage);
        return R.ok();
    }

    @PostMapping("/deleteArticle")
    public R deleteArticle(@RequestBody ArticlePojo articlePojo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
//
//
//        articleService.remove(new LambdaQueryWrapper<ArticlePojo>().eq(ArticlePojo::getUserId,userId).eq(ArticlePojo::getArticleId,articlePojo.getArticleId()));
//
//        CommentPojo commentMessage = new CommentPojo();
//        commentMessage.setArticleMessageId(articlePojo.getArticleId());
//        List<CommentPojo> list = commentService.list(new LambdaQueryWrapper<CommentPojo>().eq(CommentPojo::getArticleMessageId,articlePojo.getArticleId()));
//
//        //ReplayPojo replayMessage = new ReplayPojo();
//
//        for (CommentPojo commentPojo : list) {
//            replayService.remove(new LambdaQueryWrapper<ReplayPojo>().eq(ReplayPojo::getCommentId,commentPojo.getId()));
//        }
//        //commentService.delete(commentMessage);
//        commentService.removeById(commentMessage.getId());
//
////        NoticePojo noticeMessage = new NoticePojo();
////        noticeMessage.setArticleId(articlePojo.getArticleId());
//        noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getArticleId,articlePojo.getArticleId()));
        return articleService.deleteArticle(articlePojo,userId);
    }

    @PostMapping("/deleteReplay")
    public R deleteReplay(@PathVariable ReplayPojo replayPojo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
//
//        ReplayMessage replayMessage = new ReplayMessage();
//        replayMessage.setReplayId(replayId);
//        replayMessage.setUserId(userId);
//        replayMessageOperationService.deleteById(replayMessage);
//
//        NoticeMessage noticeMessage = new NoticeMessage();
//        noticeMessage.setReplayId(replayId);
//        noticeOperationService.delete(noticeMessage);

        return articleService.deleteReplay(replayPojo,userId);
    }

    @PostMapping("/getLikeArticle")
    public R getLikeArticle(@RequestBody ArticlePojo articlePojo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
//        LikePojo likeMessage = new LikePojo();
//        likeMessage.setArticleId(articlePojo.getArticleId());
//        likeMessage.setUserId(userId);
//
//        Integer count = likeArticleService.findCount(likeMessage);
//        if (count == 0) {
//            return ResponseData.success(false);
//        }
//        return ResponseData.success(true);
        return articleService.getLikeArticle(articlePojo,userId);
    }

    @PostMapping("/likeArticle")
    public R LikeArticle(@PathVariable ArticlePojo articlePojo, HttpServletRequest request) {
        //Integer userId = CheckAllow.checkAllow(userMessageOperationService, request);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
//        List<LikePojo> list = likeArticleService.findList(likeMessage);
//        if (list.size() == 0) {
//            likeArticleService.add(likeMessage);
//
//            LikeMessage likeMessage1 = likeArticleService.findList(likeMessage).get(0);
//
//            ArticleMessage newArticleById = articleOperationService.getNewArticleById(articleId);
//
//            NoticeMessage noticeMessage = new NoticeMessage();
//
//            noticeMessage.setArticleId(articleId);
//
//            noticeMessage.setNoticeType(2);
//            noticeMessage.setUserId(newArticleById.getUserId());
//            noticeMessage.setLikeId(likeMessage1.getLikeId());
//
//            List<NoticeMessage> noticeMessages = noticeOperationService.findList(noticeMessage);
//
//            if (noticeMessages.size() == 0 && !newArticleById.getUserId().equals(userId)) {
//
//                noticeMessage.setLikeId(likeMessage1.getLikeId());
//                noticeOperationService.add(noticeMessage);
//            }
//        } else {
//            ArticleMessage newArticleById = articleOperationService.getNewArticleById(articleId);
//
//            NoticeMessage noticeMessage = new NoticeMessage();
//
//            noticeMessage.setArticleId(articleId);
//
//            noticeMessage.setNoticeType(2);
//            noticeMessage.setUserId(newArticleById.getUserId());
//            noticeMessage.setLikeId(list.get(0).getLikeId());
//
//            likeArticleService.delete(likeMessage);
//            noticeOperationService.delete(noticeMessage);
//        }
//
//        return ResponseData.success();
        return articleService.likeArticle(articlePojo,userId);
    }
}