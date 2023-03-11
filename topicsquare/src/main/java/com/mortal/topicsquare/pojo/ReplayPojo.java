package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("replay_message")
public class ReplayPojo {
    @TableId(type = IdType.AUTO)
    private Integer replayId;

    @TableField(value = "`comment_id`")
    private Integer commentId;

    @TableField(value = "`user_id`")
    private Integer userId;

    @TableField(value = "`replay_content`")
    private String replayContent;

    @TableField(value = "`create_time`")
    private Date createTime;

    @TableField(value = "replay_user_id")
    private Integer replayUserId;

}
