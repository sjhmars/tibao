package com.mortal.topicsquare.controller;

import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.CollegePojo;
import com.mortal.topicsquare.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping("/getAllcollege")
    public R getAllTheme() {
        return R.ok(collegeService.getAllCollege());
    }

    @PostMapping("/click")
    public R click(@RequestBody CollegePojo collegePojo){
        System.out.println(collegePojo.getId());
        if (collegeService.click(collegePojo)){
            return R.ok();
        }else {
            return R.failed("计数失败");
        }
    }

    @PostMapping("/getCollegeById")
    public R getCollegeById(@RequestBody CollegePojo collegePojo){
        return R.ok(collegeService.getById(collegePojo.getId()));
    }
}
