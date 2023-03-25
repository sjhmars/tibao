package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LikeArticleVo {
    private Integer LikeId;
    private Integer LikeUser;
    private Integer articleId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lCreate;
    private String articleContent;
    private Integer articleUserId;
    private String articleImg;
    private Integer collegeId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date aCreate;
    private String userName;
    private String userType;
    private Integer sex;
    private String userImg;
}
