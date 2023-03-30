package com.moratl.questionbank.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotDoVo {
    private Integer Id;
    private String  questionTitle;
    private String answer;
    private String topicAnalysis;
    private Integer collegeId;
    private Integer solverintUser;
    private Integer qbType;
    private String choiceQuestion;
    private String choiceAnswer;
    private String qbImg;
    private Date createTime;
    private Integer isTrue;
    private String myAnswer;
    private Integer userId;
}
