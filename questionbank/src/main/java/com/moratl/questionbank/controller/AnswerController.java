package com.moratl.questionbank.controller;

import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.services.QuestionBankService;
import com.moratl.questionbank.vo.QuestionDto;
import com.mortal.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/answer")
@RestController
public class AnswerController {

    @Autowired
    private QuestionBankService questionBankService;

    @PostMapping("/saveAnswer")
    public R saveAnswer(@RequestBody QuestionBankPojo questionBankPojo){
        if (questionBankService.save(questionBankPojo))
            return R.ok("保存成功");
        return R.failed("保存失败");
    }

    @PostMapping("/getAnswer")
    public R getAnswer(){
        return questionBankService.getAnswer();
    }

    @PostMapping("/getAnswerByTitle")
    public R getAnswerByTitle(@RequestBody QuestionDto questionDto){
        return questionBankService.getAnswerByTitle(questionDto);
    }

    @PostMapping("/getAnswerNull")
    public R getAnswerNull(@RequestBody QuestionDto questionDto){
        return questionBankService.getAnswerNull(questionDto);
    }

    @PostMapping("/getAnswerHas")
    public R getAnswerHas(@RequestBody QuestionDto questionDto){
        return questionBankService.getAnswerHas(questionDto);
    }

    @PostMapping("/getAnswerById")
    public R getAnswerById(@RequestBody QuestionDto questionDto){
        return R.ok(questionBankService.getById(questionDto.getQuestionId()));
    }

    @PostMapping("/updateAnswerById")
    public R updateAnswerById(@RequestBody QuestionDto questionDto){
        return questionBankService.updateAnswerById(questionDto);
    }

    @PostMapping("/getAnswerByType")
    public R getAnswerByType(@RequestBody QuestionDto questionDto){
        return questionBankService.getAnswerByType(questionDto);
    }
}
