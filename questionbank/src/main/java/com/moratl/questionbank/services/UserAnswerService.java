package com.moratl.questionbank.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moratl.questionbank.pojo.AlreadyAnswerPojo;
import com.moratl.questionbank.vo.OperateDto;
import com.mortal.common.utils.R;

public interface UserAnswerService extends IService<AlreadyAnswerPojo> {
    R addResolution(OperateDto operateDto);
    R doAnswer(OperateDto operateDto);
    R accuracy();
    R notDo(OperateDto operateDto);
    R doWrong(OperateDto operateDto);
    R doRight(OperateDto operateDto);
    R allansweNum();
}
