package com.mortal.topicsquare.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikeArticleVo {
    private Integer LikeId;
    private Integer LikeUserId;
    private Date likeCreate;
    private String articleContent;
    private Integer articleUserId;
    private Integer collegeId;
    private Date articleCreate;
    private String userName;
    private String userType;
}
