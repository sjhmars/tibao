package com.mortal.topicsquare.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.NoticePojo;
import com.mortal.topicsquare.service.NoticeService;
import com.mortal.topicsquare.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/Notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/getNoticeMessage")
    public R getNoticeMessage(@RequestBody NoticeVo noticeVo) {
        return R.ok(noticeService.getAllNoticeById(noticeVo));
    }

    @Transactional
    @PostMapping("/deleteNotice")
    public R deleteNotice(@RequestBody NoticePojo noticePojo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        noticePojo.setUserId(userId);
        noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getNoticeId,noticePojo.getNoticeId()).eq(NoticePojo::getUserId,noticePojo.getUserId()));
        return R.ok();
    }
}
