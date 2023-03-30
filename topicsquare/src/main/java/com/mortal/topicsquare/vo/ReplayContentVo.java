package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReplayContentVo {
    private Integer replayId;
    private Integer commentId;
    private Integer userId;
    private String replayContent;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Integer replayUserId;
    private String userName;
    private String userType;
    private String userImg;
    private Integer sex;
}
