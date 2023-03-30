package com.mortal.topicsquare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mortal.topicsquare.pojo.ReplayPojo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentUserVo {
    private Integer Id;
    private String comment;
    private Integer userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ccreateTime;
    private Integer articleMessageId;
    private String userName;
    private String userType;
    private String userImg;
    private Integer sex;
    private List<ReplayContentVo> ReplayContentVoList;
}
