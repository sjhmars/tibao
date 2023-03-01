package com.mortal.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.*;

@Data
public class UserRegistVo {

    @NotEmpty(message = "必须输入用户名")
    @Length(min = 2,max = 10,message = "用户名长度在2-10之间")
    private String  userName;

    @NotEmpty(message = "密码必须填写")
    @Length(min = 2,max = 12,message = "密码长度在6-12之间")
    private String userPassword;

    @NotNull(message = "必须选择学生或老师")
    private Integer userType;

    @NotEmpty(message= "学号必须填写")
    private String jodNumber;

    @NotNull(message = "必须选择性别")
    @Min(value = 0,message = "只能选0或1")
    @Max(value = 1,message = "只能选0或1")
    private Integer sex;

    @NotEmpty(message = "手机号必须填写")
    @Pattern(regexp = "^[1][0-9]{10}$",message = "手机号格式不正确")
    private String phoneNumber;

}
