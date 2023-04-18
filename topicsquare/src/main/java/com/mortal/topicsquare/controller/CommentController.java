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
        return commentService.saveCommentAll(commentMessage,userId);
    }

    @Transactional
    @PostMapping("/saveReplay")
    public R saveReplay(@RequestBody ReplayPojo replayMessage) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer Id = loginUser.getUserPojo().getId();//当前登录用户id

        return commentService.saveReplayAll(replayMessage,Id);
    }

    @PostMapping("/getCommentById")
    public R getCommentById(@RequestBody CommentVo commentVo) {

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
