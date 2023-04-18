package com.moratl.questionbank.services.Imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moratl.questionbank.mapper.AlreadyAnswerMapper;
import com.moratl.questionbank.mapper.QuestionBankMapper;
import com.moratl.questionbank.mapper.UserMapper;
import com.moratl.questionbank.pojo.AlreadyAnswerPojo;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.pojo.UserPojo;
import com.moratl.questionbank.services.UserAnswerService;
import com.moratl.questionbank.util.NoPageUtil;
import com.moratl.questionbank.util.PageUtil;
import com.moratl.questionbank.vo.NotDoVo;
import com.moratl.questionbank.vo.NotDoVoc;
import com.moratl.questionbank.vo.OperateDto;
import com.mortal.auth.pojo.LoginUser;
import com.mortal.common.utils.R;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAnswerServiceImp extends ServiceImpl<AlreadyAnswerMapper, AlreadyAnswerPojo> implements UserAnswerService {

    @Autowired
    private AlreadyAnswerMapper alreadyAnswerMapper;

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public R addResolution(OperateDto operateDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        UserPojo userPojo = userMapper.selectById(userId);
        try {
            QuestionBankPojo questionBankPojo =  questionBankMapper.selectById(operateDto.getQbId());
            questionBankPojo.setTopicAnalysis(operateDto.getTopicAnalysis());
            questionBankPojo.setSolverintUser(userId);
            questionBankPojo.setUserType(userPojo.getUserType());
            questionBankPojo.setUserName(userPojo.getUserName());
            questionBankMapper.updateById(questionBankPojo);

            Integer answerNumber = userPojo.getAnswerNumber();
            answerNumber = answerNumber+1;
            userPojo.setAnswerNumber(answerNumber);
            userMapper.updateById(userPojo);
        }catch (Exception e){
            return R.failed(e);
        }
        return R.ok("保存解析成功");
    }

    @Override
    @Transactional
    public R doAnswer(OperateDto operateDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();

        QuestionBankPojo questionBankPojo = questionBankMapper.selectById(operateDto.getQbId());

        String choiceAnswer = questionBankPojo.getChoiceAnswer();
        List<String> chioceAnswers = Convert.toList(String.class,choiceAnswer);
        boolean falg = chioceAnswers.stream().sorted().collect(Collectors.joining()).equals
                (operateDto.getAnswerlist().stream().sorted().collect(Collectors.joining()));
        String s = String.join(",",operateDto.getAnswerlist());
        if (falg){
            AlreadyAnswerPojo alreadyAnswerPojo = new AlreadyAnswerPojo();
            alreadyAnswerPojo.setIsTrue(1);//对的是1错的是0
            alreadyAnswerPojo.setPbId(operateDto.getQbId());
            alreadyAnswerPojo.setUserId(userId);
            alreadyAnswerPojo.setMyAnswer(s);
            alreadyAnswerMapper.insert(alreadyAnswerPojo);
        }
        if (!falg){
            AlreadyAnswerPojo alreadyAnswerPojo = new AlreadyAnswerPojo();
            alreadyAnswerPojo.setIsTrue(0);//对的是1错的是0
            alreadyAnswerPojo.setPbId(operateDto.getQbId());
            alreadyAnswerPojo.setUserId(userId);
            alreadyAnswerPojo.setMyAnswer(s);
            alreadyAnswerMapper.insert(alreadyAnswerPojo);
        }
        return R.ok("进入下一题吧");
    }

    @Override
    public R accuracy() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Double AllNum =  alreadyAnswerMapper.selectAllCount(userId);
        Double right = alreadyAnswerMapper.selectAllByIsTrue(userId,1);
        double accuracy = 0;
        if (AllNum!=0){
            accuracy  = right/AllNum;
        }
        accuracy = accuracy*100;
        DecimalFormat df = new DecimalFormat("#00.00");
        return R.ok(df.format(accuracy));
    }

    @Override
    public R notDo(OperateDto operateDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Page<NotDoVo> page = new Page<>(operateDto.getPageNumber(),1);
        IPage<NotDoVo> iPage = alreadyAnswerMapper.selectNotDo(page,userId);
        List<NotDoVo> list = iPage.getRecords();
        IPage<NotDoVoc> notDoVocIPage = NoPageUtil.changeNotDoVoc(list,iPage);
        return R.ok(notDoVocIPage);
    }

    @Override
    public R doWrong(OperateDto operateDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Page<NotDoVo> page = new Page<>(operateDto.getPageNumber(),1);
        IPage<NotDoVo> iPage = alreadyAnswerMapper.selectDoWrong(page,userId);
        List<NotDoVo> list = iPage.getRecords();
        IPage<NotDoVoc> notDoVocIPage = NoPageUtil.changeNotDoVoc(list,iPage);
        return R.ok(notDoVocIPage);
    }

    @Override
    public R doRight(OperateDto operateDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Page<NotDoVo> page = new Page<>(operateDto.getPageNumber(),1);
        IPage<NotDoVo> iPage = alreadyAnswerMapper.selectDoRight(page,userId);
        List<NotDoVo> list = iPage.getRecords();
        IPage<NotDoVoc> notDoVocIPage = NoPageUtil.changeNotDoVoc(list,iPage);
        return R.ok(notDoVocIPage);
    }

    @Override
    @Transactional
    public R allansweNum() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = loginUser.getUserPojo().getId();
        Double AllNum = alreadyAnswerMapper.selectAllCount(userId);
        return R.ok(AllNum);
    }
}
