package com.moratl.questionbank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moratl.questionbank.pojo.AlreadyAnswerPojo;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import com.moratl.questionbank.vo.NotDoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AlreadyAnswerMapper extends BaseMapper<AlreadyAnswerPojo> {
    Double selectAllByIsTrue(@Param("userId") Integer userId,@Param("isTrue") Integer isTrue);
    Double selectAllCount(@Param("userId") Integer userId);
    IPage<NotDoVo> selectNotDo(IPage<NotDoVo> page, @Param("userId")Integer userId);
    IPage<NotDoVo> selectDoRight(IPage<NotDoVo> page,@Param("userId")Integer userId);
    IPage<NotDoVo> selectDoWrong(IPage<NotDoVo> page,@Param("userId")Integer userId);

}
