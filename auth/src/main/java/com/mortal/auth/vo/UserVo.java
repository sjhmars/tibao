package com.mortal.auth.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Integer id;
    private String  userName;
    private String userType;
    private Integer sex;
    private String jobNumber;
    private Integer instituteId;
    private String phoneNumber;
    private Integer answerNumber;
    private String userImg;
    private String userIntro;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
