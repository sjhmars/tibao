package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("likemessage")
public class LikePojo {
    @TableId(type = IdType.AUTO)
    private Integer likeId;

    @TableField(value = "`user_id`")
    private Integer userId;

    @TableField(value = "`article_id`")
    private Integer articleId;

    @TableField(value = "`create_time`")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}
