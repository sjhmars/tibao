package com.moratl.questionbank.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.vo.QuestionDto;
import com.mortal.common.utils.R;

public interface QuestionBankService extends IService<QuestionBankPojo> {
    R getAnswer();
    R getAnswerByTitle(QuestionDto questionDto);
    R getAnswerNull(QuestionDto questionDto);
    R getAnswerHas(QuestionDto questionDto);
    R updateAnswerById(QuestionDto questionDto);
    R getAnswerByType(QuestionDto questionDto);
    R getBigAnswer(QuestionDto questionDto);
}
