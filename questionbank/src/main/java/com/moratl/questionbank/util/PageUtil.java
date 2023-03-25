package com.moratl.questionbank.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.vo.QuestionBankVo;

import java.util.List;
import java.util.stream.Collectors;

public class PageUtil {
    public static IPage<QuestionBankVo> change(List<QuestionBankPojo> questionBankPojos,IPage<QuestionBankPojo> iPage){
        List<QuestionBankVo> questionBankVos = questionBankPojos.stream().map(questionBankPojo -> {
            List<String> choiceQuestions = null;
            if (StrUtil.isNotBlank(questionBankPojo.getChoiceQuestion())){
                choiceQuestions = Convert.toList(String.class,questionBankPojo.getChoiceQuestion());
            }
            List<String> choiceAnswers = null;
            if (StrUtil.isNotBlank(questionBankPojo.getChoiceAnswer())){
                choiceAnswers = Convert.toList(String.class,questionBankPojo.getChoiceAnswer());
            }
            QuestionBankVo questionBankVo = new QuestionBankVo(questionBankPojo.getId(),questionBankPojo.getQuestionTitle(),questionBankPojo.getAnswer(),questionBankPojo.getTopicAnalysis(),questionBankPojo.getCollegeId(),questionBankPojo.getSolverintUser(),questionBankPojo.getQbType(),choiceQuestions,choiceAnswers,questionBankPojo.getQbImg());
            return questionBankVo;
        }).collect(Collectors.toList());
        IPage<QuestionBankVo> questionBankVoIPage = new Page<>();
        questionBankVoIPage.setCurrent(iPage.getCurrent())
                .setRecords(questionBankVos)
                .setTotal(iPage.getTotal())
                .setSize(iPage.getSize())
                .setPages(iPage.getPages());
        return questionBankVoIPage;
    }
}
