package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mortal.topicsquare.pojo.ReplayPojo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentUserVo {
    private Integer commentId;
    private String comment;
    private Integer userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Integer articleMessageId;
    private String userName;
    private String userType;
    private String userImg;
    private List<ReplayContentVo> ReplayContentVoList;
}
