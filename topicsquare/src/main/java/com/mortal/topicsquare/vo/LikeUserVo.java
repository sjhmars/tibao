package com.mortal.topicsquare.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikeUserVo {
    private Integer likeId;
    private Integer userId;
    private Integer articleId;
    private Date createTime;
    private String userName;
    private String userType;
    private String userImg;
}
