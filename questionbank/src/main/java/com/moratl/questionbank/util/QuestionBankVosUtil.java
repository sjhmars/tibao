package com.moratl.questionbank.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.vo.QuestionBankVo;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionBankVosUtil {
    public static List<QuestionBankVo> change(List<QuestionBankPojo> questionBankPojos){
        List<QuestionBankVo> questionBankVos = questionBankPojos.stream().map(questionBankPojo -> {
            List<String> choiceQuestions = null;
            if (StrUtil.isNotBlank(questionBankPojo.getChoiceQuestion())){
                choiceQuestions = Convert.toList(String.class,questionBankPojo.getChoiceQuestion());
            }
            List<String> choiceAnswers = null;
            if (StrUtil.isNotBlank(questionBankPojo.getChoiceAnswer())){
                choiceAnswers = Convert.toList(String.class,questionBankPojo.getChoiceAnswer());
            }
            QuestionBankVo questionBankVo = new QuestionBankVo(questionBankPojo.getId(),questionBankPojo.getQuestionTitle(),questionBankPojo.getAnswer(),questionBankPojo.getTopicAnalysis(),questionBankPojo.getCollegeId(),questionBankPojo.getSolverintUser(),questionBankPojo.getQbType(),choiceQuestions,choiceAnswers,questionBankPojo.getQbImg(),questionBankPojo.getUserType(),questionBankPojo.getUserName());
            return questionBankVo;
        }).collect(Collectors.toList());
        return questionBankVos;
    }
}
