package com.mortal.topicsquare.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleUserVo {
    private Integer articleId;
    private String  articleContent;
    private String options;
    private String userName;
    private String userType;
    private String userImg;
    private String collegeName;
    private Date createTime;
}
