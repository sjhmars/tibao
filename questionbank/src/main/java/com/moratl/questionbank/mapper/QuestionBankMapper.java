package com.moratl.questionbank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moratl.questionbank.pojo.QuestionBankPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionBankMapper extends BaseMapper<QuestionBankPojo> {
    List<QuestionBankPojo> searchAll();
}
