package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("like")
public class LikePojo {
    @TableId(type = IdType.AUTO)
    private Integer likeId;

    @TableField(value = "`user_id`")
    private Integer userId;

    @TableField(value = "`article_id`")
    private Integer articleId;

    @TableField(value = "`create_time`")
    private Date createTime;

}
