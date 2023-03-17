package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mortal.topicsquare.pojo.ArticlePojo;
import com.mortal.topicsquare.vo.ArticleUserVo;
import com.mortal.topicsquare.vo.LikeArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePojo> {
    IPage<ArticleUserVo> selectAllArticle(IPage<ArticleUserVo> page, @Param("articleId") Integer articleId,@Param("collegeId") Integer collegeId,@Param("userId") Integer userId);
    ArticleUserVo selectByArticleId(@Param("articleId") Integer articleId);
    IPage<LikeArticleVo> selectByArticleIdLikeArticleVo(IPage<LikeArticleVo> page,@Param("userId")Integer userId);
}
