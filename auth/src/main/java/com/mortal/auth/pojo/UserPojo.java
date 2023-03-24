package com.mortal.auth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserPojo {

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "user_name")
    private String  userName;

    @TableField(value = "user_Password")
    private String userPassword;

    @TableField(value = "user_type")
    private String userType;

    @TableField(value = "is_delete")
    private Integer isDelete;

    private Integer sex;

    @TableField("job_number")
    private String jobNumber;

    @TableField("institute_id")
    private Integer instituteId;

    private Integer status;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("answer_number")
    private Integer answerNumber;

    @TableField("conceal")
    private String conceal;

    @TableField("user_img")
    private String userImg;

    @TableField("user_intro")
    private String userIntro;

    @TableField("create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
