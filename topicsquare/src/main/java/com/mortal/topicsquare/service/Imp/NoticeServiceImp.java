package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.topicsquare.mapper.NoticeMapper;
import com.mortal.topicsquare.pojo.LikePojo;
import com.mortal.topicsquare.pojo.NoticePojo;
import com.mortal.topicsquare.pojo.ReplayPojo;
import com.mortal.topicsquare.service.CommentService;
import com.mortal.topicsquare.service.LikeService;
import com.mortal.topicsquare.service.NoticeService;
import com.mortal.topicsquare.service.ReplayService;
import com.mortal.topicsquare.vo.NoticeVo;
import com.mortal.topicsquare.vo.ReplayContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImp extends ServiceImpl<NoticeMapper, NoticePojo> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplayService replayService;

    @Override
    public IPage<NoticePojo> getAllNoticeById(NoticeVo noticeVo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Page<NoticePojo> page = new Page<>(noticeVo.getPageNumber(),10);

        IPage<NoticePojo> result = noticeMapper.selectAllByUserId(page,userId);

        List<NoticePojo> list = result.getRecords();

        for (NoticePojo noticePojo : list) {
            switch (noticePojo.getNoticeType()) {
                case 2://收藏
                    //返回收藏加user信息,这个方法未使用
                    noticePojo.setLikeUserVo(likeService.getLikeById(noticePojo.getLikeId()));
                    break;
                case 3://评论
                    noticePojo.setCommentUserVo(commentService.getCommentById(noticePojo.getCommentId()));
                    break;
                case 4://回复
                    ReplayContentVo replayContentVo = replayService.getReplayContentById(noticePojo.getReplayId());
//                    if (replayContentVo.getReplayUserId() != null) {
//                        replayContentVo.setCommentUserMessage(replayMessage.getReplayUserMessage());
//                    }
                    noticePojo.setReplayContentVo(replayContentVo);
                    break;
            }
        }
        result.setRecords(list);
        return result;
    }
}
