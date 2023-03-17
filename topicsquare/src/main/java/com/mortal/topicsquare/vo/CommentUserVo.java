package com.mortal.topicsquare.vo;

import com.mortal.topicsquare.pojo.ReplayPojo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentUserVo {
    private Integer commentId;
    private String comment;
    private Integer userId;
    private Date createTime;
    private Integer articleMessageId;
    private String userName;
    private String userType;
    private String userImg;
    private List<ReplayContentVo> ReplayContentVoList;
}
