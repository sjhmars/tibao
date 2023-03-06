package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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

    @TableField("college_id")
    private Integer collegeId;

    private Integer status;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("answer_number")
    private Integer answerNumber;

    @TableField("conceal")
    private String conceal;
}
