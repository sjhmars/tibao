package com.mortal.topicsquare.controller;

import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.enums.CodeEnum;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.*;
import com.mortal.topicsquare.vo.CommentUserVo;
import com.mortal.topicsquare.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping("/saveComment")
    @Transactional
    public R saveComment(@RequestBody CommentPojo commentMessage) {
//        CommentPojo commentPojo = new CommentPojo();
//先添加，在查询，然后在保存通知
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
//        commentPojo.setUserId(userId);
//        commentPojo.setArticleMessageId(commentMessage.getArticleMessageId());
//        commentPojo.setComment(commentMessage.getComment());
//        commentPojo.setIsDelete(0);
//        commentPojo.setCreateTime(new Date());
//        commentService.save(commentPojo);
//
//
//        ArticlePojo newArticle = articleService.getById(commentMessage.getArticleMessageId());
//
//        if (!newArticle.getUserId().equals(userId)) {
//            NoticePojo noticeMessage = new NoticePojo();
//            noticeMessage.setUserId(newArticle.getUserId());
//            noticeMessage.setNoticeType(3);
//            noticeMessage.setArticleId(commentMessage.getArticleMessageId());
//            noticeMessage.setCommentId(commentPojo.getId());
//
//            noticeService.save(noticeMessage);
//        }
        return commentService.saveCommentAll(commentMessage,userId);
    }

    @Transactional
    @PostMapping("/saveReplay")
    public R saveReplay(@RequestBody ReplayPojo replayMessage) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer Id = loginUser.getUserPojo().getId();//当前登录用户id
//        replayMessage.setUserId(Id);
//        replayMessage.setCreateTime(new Date());
//
//        NoticePojo noticeMessage = new NoticePojo();
//        noticeMessage.setReplayId(replayMessage.getReplayId());
//        noticeMessage.setNoticeType(4);
//
//        CommentPojo commentPojo = commentService.getById(replayMessage.getCommentId());//获取回复的评论内容
//        noticeMessage.setArticleId(commentPojo.getArticleMessageId());//关联文章
//        if (replayMessage.getReplayUserId() == null && commentPojo.getUserId().equals(Id)) {
//            noticeMessage.setUserId(commentPojo.getUserId());
//            replayMessage.setReplayUserId(commentPojo.getUserId());
//            noticeService.save(noticeMessage);
//        } else if (replayMessage.getReplayUserId() != null && !replayMessage.getReplayUserId().equals(Id)) {
//            noticeMessage.setUserId(replayMessage.getReplayUserId());
//            noticeService.save(noticeMessage);
//        }
//        replayService.save(replayMessage);
        return commentService.saveReplayAll(replayMessage,Id);
    }

    @PostMapping("/getCommentById")
    public R getCommentById(@RequestBody CommentVo commentVo) {
//        for ( CommentUserVo commentUserVo : list) {
//            commentUserVo.setReplayPojoList();
//        }
//        int number = list.size();
//        for (int i = 0; i < number; i++) {
//            list.get(i).setReplayPojoList(replayMessageOperationService.getReplayContent(list.get(i).getCommentId()));
//        }
//
//        PageInfo<CommentMessage> of = PageInfo.of(list);
        return R.ok(commentService.getCommentByArticleId(commentVo));
    }

    @PostMapping("/deleteComment")
    @Transactional
    public R deleteComment(@RequestBody Integer commentId) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        if (commentService.removeComment(commentId,userId) == 0){
            return R.ok("删除回复成功");
        }
        return R.failed("删除失败");
    }
}
