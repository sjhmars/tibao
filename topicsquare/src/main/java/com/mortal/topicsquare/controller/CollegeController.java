package com.mortal.topicsquare.controller;

import com.mortal.common.utils.R;
import com.mortal.topicsquare.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;
    @PostMapping("/getAllcollege")
    public R getAllTheme() {
        return R.ok(collegeService.getAllCollege());
    }
}
