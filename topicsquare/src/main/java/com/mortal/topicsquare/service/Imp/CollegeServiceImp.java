package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.CollegeMapper;
import com.mortal.topicsquare.pojo.CollegePojo;
import com.mortal.topicsquare.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImp extends ServiceImpl<CollegeMapper, CollegePojo> implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<CollegePojo> getAllCollege() {
        return collegeMapper.searchAll();
    }

    @Override
    public boolean click(CollegePojo collegePojo) {
        CollegePojo collegePojo1 = collegeMapper.selectById(collegePojo.getId());
        collegePojo1.setClickNum(collegePojo1.getClickNum()+1);
        return collegeMapper.updateById(collegePojo1) > 0;
    }
}
