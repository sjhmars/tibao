package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class CommentPojo {

    @TableId(type = IdType.AUTO)
    private Integer Id;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(value = "article_message_id" )
    private Integer articleMessageId;


    @TableField(value = "create_time")
    private Date createTime;
}
