package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Date createTime;

    @TableField(value = "option")
    private String option;
}
