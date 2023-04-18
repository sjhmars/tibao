package com.moratl.questionbank.vo;

import lombok.Data;

@Data
public class QuestionDto {
    private String questionTitle;
    private Integer pageNumber;
    private Integer questionId;
    private Integer qbType;
    private String answer;
    private Integer collegeId;
}
