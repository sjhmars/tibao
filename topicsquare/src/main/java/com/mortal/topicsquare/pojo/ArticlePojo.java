package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("article_message")
public class ArticlePojo {
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    @TableField(value = "article_content")
    private String articleContent;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "college_id")
    private Integer collegeId;

    @TableField(value = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//转换返回时间格式
    private Date createTime;

    @TableField(value = "options")
    private String options;

    @TableField(value = "article_type")
    private Integer articleType;

    @TableField(value = "article_img")
    private String articleImg;
}
