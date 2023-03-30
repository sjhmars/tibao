package com.mortal.topicsquare.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("question_bank")
public class QuestionBankPojo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "question_title")
    private String questionTitle;

    @TableField(value = "answer")
    private String answer;

    @TableField(value = "topic_analysis")
    private String topicAnalysis;

    @TableField(value = "college_id")
    private Integer collegeId;

    @TableField(value = "solverint_user")
    private Integer solverintUser;

    @TableField(value = "qb_type")
    private Integer qbType;

    @TableField(value = "choice_question")
    private String choiceQuestion;

    @TableField(value = "choice_answer")
    private String choiceAnswer;

    @TableField(value = "qb_img")
    private String qbImg;

    @TableField(value = "create_time")
    private Date createTime;
}
