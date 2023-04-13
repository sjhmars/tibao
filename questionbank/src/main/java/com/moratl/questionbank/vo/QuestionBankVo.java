package com.moratl.questionbank.vo;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBankVo {

    private Integer id;


    private String questionTitle;


    private String answer;


    private String topicAnalysis;


    private Integer collegeId;


    private Integer solverintUser;


    private Integer qbType;


    private List<String> choiceQuestion;


    private List<String> choiceAnswer;


    private String qbImg;

    private String userType;

    private String userName;
}
