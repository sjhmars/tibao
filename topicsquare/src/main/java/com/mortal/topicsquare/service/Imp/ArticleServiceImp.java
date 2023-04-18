package com.mortal.topicsquare.service.Imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.feign.SaveTitleService;
import com.mortal.topicsquare.mapper.ArticleMapper;
import com.mortal.topicsquare.pojo.*;
import com.mortal.topicsquare.service.*;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.ArticleVo;
import com.mortal.topicsquare.vo.LikeArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SaveTitleService saveTitleService;

    @Autowired
    private UserService userService;

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
        ArticleUserVo articleUserVo = articleMapper.selectByArticleId(articleId);
        String options = null;
        if (StrUtil.isNotBlank(articleUserVo.getOptions()))
             options = articleUserVo.getOptions().replace(","," ");
        articleUserVo.setOptions(options);
        return articleUserVo;
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

    @Override
    public R saveToqb(ArticlePojo articlePojo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        articlePojo.setUserId(userId);
        articlePojo.setCreateTime(new Date());
        this.save(articlePojo);
        QuestionBankPojo questionBankPojo = new QuestionBankPojo();
        if (articlePojo.getArticleType() == 0){
            String answer = null;
            if (StrUtil.isNotBlank(articlePojo.getOptions())){
                answer = articlePojo.getOptions().substring(1);
                answer = answer.substring(0,answer.length()-1);
            }
            questionBankPojo.setAnswer(answer);
            questionBankPojo.setCollegeId(articlePojo.getCollegeId());
            if (StrUtil.isNotBlank(articlePojo.getArticleImg()))
                questionBankPojo.setQbImg(articlePojo.getArticleImg());
            questionBankPojo.setQbType(articlePojo.getArticleType());
            List<String> answerAndChoice = Arrays.asList(articlePojo.getArticleContent().split("#"));
            questionBankPojo.setQuestionTitle(answerAndChoice.get(1));
        }
        if (articlePojo.getArticleType() == 1){
            if (StrUtil.isNotBlank(articlePojo.getArticleContent())){
                List<String> answerAndChoice = Arrays.asList(articlePojo.getArticleContent().split("#"));
                questionBankPojo.setQuestionTitle(answerAndChoice.get(1));
                questionBankPojo.setChoiceQuestion(answerAndChoice.get(2));
            }
            questionBankPojo.setCollegeId(articlePojo.getCollegeId());
            questionBankPojo.setQbImg(articlePojo.getArticleImg());
            questionBankPojo.setQbType(articlePojo.getArticleType());
            questionBankPojo.setChoiceAnswer(articlePojo.getOptions());
        }
        questionBankPojo.setCreateTime(new Date());

            saveTitleService.saveAnswer(questionBankPojo);
        return R.ok("保存成功");
    }

    @Override
    public IPage<ArticleUserVo> getArticleByName(ArticleVo articleVo) {
        List<UserPojo> userPojoList = userService.list(new LambdaQueryWrapper<UserPojo>().like(UserPojo::getUserName,articleVo.getUserName()));
        List<Integer> ids = userPojoList.stream().map(UserPojo::getId).collect(Collectors.toList());
        Page<ArticleUserVo> page = new Page<>(articleVo.getPageNumber(),10);
        return articleMapper.selectAllByUserIds(page,ids);
    }
}

