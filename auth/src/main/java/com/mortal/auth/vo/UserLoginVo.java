package com.mortal.auth.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginVo {

    private String userName;

    @NotEmpty
    private String userPassword;

    @NotEmpty
    @Pattern(regexp = "^[1][0-9]{10}$",message = "手机号格式不正确")
    private String phoneNumber;
}
