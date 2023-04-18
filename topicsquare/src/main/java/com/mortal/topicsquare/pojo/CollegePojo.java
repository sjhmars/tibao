package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("college")
public class CollegePojo {
    @TableId(type = IdType.AUTO)
    private Integer Id;

    @TableField(value = "college_name")
    private String collegeName;

    @TableField(value = "college_intor")
    private String collegeIntor;

    @TableField(value = "college_image")
    private String collegeImage;

    @TableField(value = "click_num")
    private Integer clickNum;

}
