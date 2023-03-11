package com.mortal.topicsquare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mortal.topicsquare.pojo.ArticlePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePojo> {
}
