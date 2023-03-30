package com.moratl.questionbank.vo;

import lombok.Data;

import java.util.List;

@Data
public class OperateDto {
    private Integer userId;
    private Integer qbId;
    private String topicAnalysis;
    private String answer;
    private Integer pageNumber;
    private List<String> answerlist;
}
