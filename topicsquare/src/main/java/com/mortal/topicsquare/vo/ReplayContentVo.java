package com.mortal.topicsquare.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReplayContentVo {
    private Integer replayId;
    private Integer commentId;
    private Integer userId;
    private String replayContent;
    private Date createTime;
    private Integer replayUserId;
    private String userName;
    private String userType;
    private String userImg;
}
