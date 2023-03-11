package com.mortal.topicsquare.controller;

import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.enums.CodeEnum;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplayService replayService;


    @RequestMapping("/saveComment")
    public R saveComment(@RequestBody CommentPojo commentMessage) {
        CommentPojo commentPojo = new CommentPojo();
//先添加，在查询，然后在保存通知
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        commentPojo.setUserId(userId);

        commentService.save(commentPojo);


        ArticlePojo newArticle = articleService.getById(commentMessage.getArticleMessageId());

        if (!newArticle.getUserId().equals(userId)) {
            NoticePojo noticeMessage = new NoticePojo();
            noticeMessage.setUserId(newArticle.getUserId());
            noticeMessage.setNoticeType(3);
            noticeMessage.setArticleId(commentMessage.getArticleMessageId());
            noticeMessage.setCommentId(commentMessage.getId());

            noticeService.save(noticeMessage);
        }
        return R.ok("发布成功");
    }

    @Transactional
    @RequestMapping("/saveReplay")
    public R saveReplay(@RequestBody ReplayPojo replayMessage) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer Id = loginUser.getUserPojo().getId();//当前登录用户id

        Integer userId = replayMessage.getUserId();

        if (userService.getById(userId) == null) {
            return R.failed("用户数据错误");
        }

        //WXSessionModel user = (WXSessionModel) request.getSession().getAttribute("user");


        replayMessage.setUserId(Id);

        replayService.save(replayMessage);


        //ReplayPojo replayMessage1 = replayService.findList(replayMessage).get(0);

        //System.out.println(replayMessage1);

        NoticePojo noticeMessage = new NoticePojo();
        noticeMessage.setReplayId(replayMessage.getReplayId());
        noticeMessage.setNoticeType(4);

//        CommentPojo commentMessage = new CommentPojo();
//        commentMessage.setId(replayMessage.getCommentId());

//        CommentMessage commentMessage1 = commentService.findList(commentMessage).get(0);
        CommentPojo commentPojo = commentService.getById(replayMessage.getCommentId());//获取回复的评论内容
        noticeMessage.setArticleId(commentPojo.getArticleMessageId());//关联文章
        if (replayMessage.getReplayUserId() == null && !commentPojo.getUserId().equals(Id)) {
            noticeMessage.setUserId(commentPojo.getUserId());
//            replayMessage.setReplayUserId(commentPojo.getUserId());
            noticeService.save(noticeMessage);
        } else if (!replayMessage.getReplayUserId().equals(Id) && replayMessage.getReplayUserId() != null) {
            noticeMessage.setUserId(replayMessage.getReplayUserId());
            noticeService.save(noticeMessage);
        }
        return R.ok("发布评论成功");
    }
}
