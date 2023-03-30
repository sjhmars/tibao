package com.moratl.questionbank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("already_answer")
public class AlreadyAnswerPojo {

    @TableId(type = IdType.AUTO)
    @TableField(value = "already_answer_id")
    private Integer alreadyAnswerId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "is_true")
    private Integer isTrue;

    @TableField(value = "qb_id")
    private Integer pbId;

    @TableField(value = "my_answer")
    private String myAnswer;
}
