package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleUserVo {
    private Integer articleId;
    private String  articleContent;
    private String articleImg;
    private String options;
    private Integer userId;
    private String userName;
    private String userType;
    private String userImg;
    private String Sex;
    private String collegeName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
