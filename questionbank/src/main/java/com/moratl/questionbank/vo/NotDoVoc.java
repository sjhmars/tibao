package com.moratl.questionbank.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotDoVoc {
    private Integer Id;
    private String  questionTitle;
    private String answer;
    private String topicAnalysis;
    private Integer collegeId;
    private Integer solverintUser;
    private Integer qbType;
    private List<String> choiceQuestion;
    private List<String> choiceAnswer;
    private String qbImg;
    private Date createTime;
    private String userType;
    private String userName;
    private Integer isTrue;
    private List<String> myAnswer;
    private Integer userId;
}
