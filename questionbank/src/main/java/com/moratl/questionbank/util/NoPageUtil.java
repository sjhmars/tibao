package com.moratl.questionbank.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moratl.questionbank.vo.NotDoVo;
import com.moratl.questionbank.vo.NotDoVoc;

import java.util.List;
import java.util.stream.Collectors;

public class NoPageUtil {
    public static IPage<NotDoVoc> changeNotDoVoc(List<NotDoVo> list,IPage<NotDoVo> iPage){
        List<NotDoVoc> notDoVocs = list.stream().map(notDoVo -> {
            List<String> choiceQuestions = null;
            if (StrUtil.isNotBlank(notDoVo.getChoiceQuestion()))
                choiceQuestions = Convert.toList(String.class, notDoVo.getChoiceQuestion());
            List<String> choiceAnswers = null;
            if (StrUtil.isNotBlank(notDoVo.getChoiceAnswer())){
                choiceAnswers = Convert.toList(String.class, notDoVo.getChoiceAnswer());
            }
            List<String> MyAnswers = null;
            if (StrUtil.isNotBlank(notDoVo.getMyAnswer())){
                MyAnswers = Convert.toList(String.class,notDoVo.getAnswer());
            }
            return new NotDoVoc(notDoVo.getId(),notDoVo.getQuestionTitle(),notDoVo.getAnswer(),notDoVo.getTopicAnalysis(),notDoVo.getCollegeId(),notDoVo.getSolverintUser(),notDoVo.getQbType(),choiceQuestions,choiceAnswers,notDoVo.getQbImg(),notDoVo.getCreateTime(),notDoVo.getIsTrue(),MyAnswers,notDoVo.getUserId());
        }).collect(Collectors.toList());
        IPage<NotDoVoc> notDoVocIPage = new Page<>();
        notDoVocIPage.setCurrent(iPage.getCurrent())
                .setRecords(notDoVocs)
                .setTotal(iPage.getTotal())
                .setSize(iPage.getSize())
                .setPages(iPage.getPages());
        return notDoVocIPage;
    }
}
