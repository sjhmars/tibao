package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mortal.topicsquare.vo.CommentUserVo;
import com.mortal.topicsquare.vo.LikeUserVo;
import com.mortal.topicsquare.vo.ReplayContentVo;
import lombok.Data;

import java.util.Date;

@Data
@TableName("notice_message")
public class NoticePojo {

    @TableId(type = IdType.AUTO)
    private Integer noticeId;

    @TableField(value = "`notice_type`")
    private Integer noticeType;

    @TableField(value = "`user_id`")
    private Integer userId;//1

    @TableField(value = "`replay_id`")
    private Integer replayId;//2

    @TableField(value = "`like_id`")
    private Integer likeId;//3

    @TableField(value = "`comment_id`")
    private Integer commentId;//4

    @TableField(value = "`article_id`")
    private Integer articleId;//2/3/4

    @TableField(value = "`create_time`")
    private Date createTime;

    @TableField(value = "`content`")
    private String content;//1

    @TableField(value = "`send_user_id`")
    private Integer sendUserId;

    private LikeUserVo likeUserVo;

    private CommentUserVo commentUserVo;

    private ReplayContentVo replayContentVo;
}

