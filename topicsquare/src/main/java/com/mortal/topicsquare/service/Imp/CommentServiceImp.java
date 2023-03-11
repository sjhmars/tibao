package com.mortal.topicsquare.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mortal.topicsquare.mapper.CommentMapper;
import com.mortal.topicsquare.pojo.CommentPojo;
import com.mortal.topicsquare.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper, CommentPojo> implements CommentService {
}
