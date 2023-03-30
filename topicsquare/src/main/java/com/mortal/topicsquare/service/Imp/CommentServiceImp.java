package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.mapper.CommentMapper;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.*;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.CommentUserVo;
import com.mortal.topicsquare.vo.CommentVo;
import com.mortal.topicsquare.vo.ReplayContentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper, CommentPojo> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplayService replayService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    @Override
    public IPage<CommentUserVo> getCommentByArticleId(CommentVo commentVo) {
        Page<CommentUserVo> page = new Page<>(commentVo.getPageNumber(),10);
        List<CommentUserVo> list =  commentMapper.selectComment(page,commentVo.getArticleId()).getRecords();
        for ( CommentUserVo commentUserVo : list) {
            List<ReplayContentVo> replayContentVos = replayService.getReplayContent(commentUserVo.getId());
            replayContentVos = replayContentVos.stream().map(replayContentVo -> {
                UserPojo user_name = userService.getOne(new LambdaUpdateWrapper<UserPojo>().eq(UserPojo::getId,replayContentVo.getReplayUserId()));
                replayContentVo.setUserName(user_name.getUserName());
                return replayContentVo;
            }).collect(Collectors.toList());
            commentUserVo.setReplayContentVoList(replayContentVos);
        }
//        int number = list.size();
//        for (int i = 0; i < number; i++) {
//            list.get(i).setReplayPojoList(replayService.getReplayContent(list.get(i).getCommentId()));
//        }

//        PageInfo<CommentMessage> of = PageInfo.of(list);
        page.setRecords(list);
        return page;
    }

    @Override
    public R saveCommentAll(CommentPojo commentMessage,Integer userId) {
        CommentPojo commentPojo = new CommentPojo();
        commentPojo.setUserId(userId);
        commentPojo.setArticleMessageId(commentMessage.getArticleMessageId());
        commentPojo.setComment(commentMessage.getComment());
        commentPojo.setIsDelete(0);
        commentPojo.setCreateTime(new Date());
        this.save(commentPojo);


        ArticlePojo newArticle = articleService.getById(commentMessage.getArticleMessageId());

        if (!newArticle.getUserId().equals(userId)) {
            NoticePojo noticeMessage = new NoticePojo();
            noticeMessage.setUserId(newArticle.getUserId());
            noticeMessage.setNoticeType(3);
            noticeMessage.setArticleId(commentMessage.getArticleMessageId());
            noticeMessage.setCommentId(commentPojo.getId());
            noticeMessage.setCreateTime(new Date());
            noticeService.save(noticeMessage);
        }
        return R.ok("发布成功");
    }

    @Override
    public R saveReplayAll(ReplayPojo replayPojo, Integer userId) {
        replayPojo.setUserId(userId);
        replayPojo.setCreateTime(new Date());

        NoticePojo noticeMessage = new NoticePojo();
        noticeMessage.setReplayId(replayPojo.getReplayId());
        noticeMessage.setCreateTime(new Date());
        noticeMessage.setNoticeType(4);

        CommentPojo commentPojo = this.getById(replayPojo.getCommentId());//获取回复的评论内容
        noticeMessage.setArticleId(commentPojo.getArticleMessageId());//关联文章
        if (replayPojo.getReplayUserId() == null && commentPojo.getUserId().equals(userId)) {
            noticeMessage.setUserId(commentPojo.getUserId());
            replayPojo.setReplayUserId(commentPojo.getUserId());
            noticeService.save(noticeMessage);
        } else if (replayPojo.getReplayUserId() != null && !replayPojo.getReplayUserId().equals(userId)) {
            noticeMessage.setUserId(replayPojo.getReplayUserId());
            noticeMessage.setReplayId(replayPojo.getUserId());
            noticeService.save(noticeMessage);
        }
        replayService.save(replayPojo);
        return R.ok("发布回复成功");
    }

    @Override
    public CommentUserVo getCommentById(Integer commentId) {
        return commentMapper.selectCommentUserVo(commentId);
    }

    @Override
    public Integer removeComment(Integer commentId, Integer userId) {
        CommentPojo commentMessage = new CommentPojo();
        commentMessage.setId(commentId);
        commentMessage.setUserId(userId);

        commentMapper.delete(new LambdaQueryWrapper<CommentPojo>().eq(CommentPojo::getId,commentId));

        noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getCommentId,commentId));

        List<ReplayPojo> list = replayService.list(new LambdaQueryWrapper<ReplayPojo>().eq(ReplayPojo::getCommentId,commentId));

        replayService.remove(new LambdaQueryWrapper<ReplayPojo>().eq(ReplayPojo::getCommentId,commentId));

        for (ReplayPojo replayPojo : list) {
            noticeService.remove(new LambdaQueryWrapper<NoticePojo>().eq(NoticePojo::getReplayId,replayPojo.getReplayId()));
        }
        return 0;
    }
}
