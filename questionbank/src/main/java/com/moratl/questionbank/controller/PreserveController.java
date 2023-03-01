package com.moratl.questionbank.controller;

import com.mortal.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Preserve")
public class PreserveController {
    @PostMapping("/save")
    public R save(){
        return null;
    }
}
