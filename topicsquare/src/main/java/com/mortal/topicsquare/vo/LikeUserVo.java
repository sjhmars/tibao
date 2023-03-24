package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LikeUserVo {
    private Integer likeId;
    private Integer userId;
    private Integer articleId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private String userName;
    private String userType;
    private String userImg;
}
