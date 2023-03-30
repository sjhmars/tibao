package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("my_picture")
public class SwiperPojo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "image")
    private String image;
}
