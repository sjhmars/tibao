package com.moratl.questionbank.services.Imp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moratl.questionbank.mapper.QuestionBankMapper;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.services.QuestionBankService;
import com.moratl.questionbank.util.PageUtil;
import com.moratl.questionbank.util.QuestionBankVosUtil;
import com.moratl.questionbank.vo.QuestionBankVo;
import com.moratl.questionbank.vo.QuestionDto;
import com.mortal.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankServiceImp extends ServiceImpl<QuestionBankMapper, QuestionBankPojo> implements QuestionBankService {

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public R getAnswer() {
        List<QuestionBankPojo> questionBankPojos = questionBankMapper.searchAll();
        /**抽象分离这个方法到工具类
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
         **/
        List<QuestionBankVo> questionBankVos = QuestionBankVosUtil.change(questionBankPojos);
        return R.ok(questionBankVos);
    }

    @Override
    public R getAnswerByTitle(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectPage(page,new LambdaQueryWrapper<QuestionBankPojo>().like(QuestionBankPojo::getQuestionTitle, questionDto.getQuestionTitle()));
        /**
         * 这个也抽离掉了
         * List<QuestionBankPojo> list = iPage.getRecords();
         *         List<QuestionBankVo> questionBankVos = QuestionBankVosUtil.change(list);
         * //        iPage.setRecords(questionBankVos);
         *         IPage<QuestionBankVo> questionBankVoIPage = new Page<>();
         *         questionBankVoIPage.setCurrent(iPage.getCurrent())
         *                 .setRecords(questionBankVos)
         *                 .setTotal(iPage.getTotal())
         *                 .setSize(iPage.getSize())
         *                 .setPages(iPage.getPages());
         */
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }

    @Override
    public R getAnswerNull(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectNoAnswer(page);
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }
    @Override
    public R getAnswerHas(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectPage(page,new LambdaQueryWrapper<QuestionBankPojo>().ne(QuestionBankPojo::getAnswer,"").or().ne(QuestionBankPojo::getChoiceAnswer,""));
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }

    @Override
    public R updateAnswerById(QuestionDto questionDto) {
        QuestionBankPojo questionBankPojo = questionBankMapper.selectById(questionDto.getQuestionId());
        if (questionBankPojo.getQbType() == 1){
            questionBankPojo.setChoiceAnswer(questionDto.getAnswer());
            questionBankMapper.update(questionBankPojo,new LambdaUpdateWrapper<QuestionBankPojo>().eq(QuestionBankPojo::getId,questionDto.getQuestionId()));
        }
        if (questionBankPojo.getQbType() == 0){
            String answer = null;
            if (StrUtil.isNotBlank(questionDto.getAnswer())){
                answer = questionDto.getAnswer().substring(1);
                answer = answer.substring(0,answer.length()-1);
            }
            questionBankPojo.setAnswer(answer);
            questionBankMapper.update(questionBankPojo,new LambdaUpdateWrapper<QuestionBankPojo>().eq(QuestionBankPojo::getId,questionDto.getQuestionId()));
        }
        return R.ok("更新成功");
    }

    @Override
    public R getAnswerByType(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectPage(page,new LambdaQueryWrapper<QuestionBankPojo>().eq(QuestionBankPojo::getQbType,questionDto.getQbType()).and(qw ->qw.ne(QuestionBankPojo::getAnswer,"").or().ne(QuestionBankPojo::getChoiceAnswer,"")));
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }

    @Override
    public R getBigAnswer(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectPage(page,new LambdaQueryWrapper<QuestionBankPojo>().eq(QuestionBankPojo::getQbType,0));
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }

    @Override
    public R getAnswerByCollegeId(QuestionDto questionDto) {
        Page<QuestionBankPojo> page = new Page<>(questionDto.getPageNumber(),1);
        IPage<QuestionBankPojo> iPage = questionBankMapper.selectPage(page,new LambdaQueryWrapper<QuestionBankPojo>().eq(QuestionBankPojo::getCollegeId,questionDto.getCollegeId()).and(qw ->qw.ne(QuestionBankPojo::getAnswer,"").or().ne(QuestionBankPojo::getChoiceAnswer,"")));
        IPage<QuestionBankVo> questionBankVoIPage = PageUtil.change(iPage.getRecords(),iPage);
        return R.ok(questionBankVoIPage);
    }

}
