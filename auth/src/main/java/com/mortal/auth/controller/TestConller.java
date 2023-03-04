package com.mortal.auth.controller;

import com.mortal.common.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestConller {
    @PostMapping("/hh")
    @PreAuthorize("hasAuthority('test')")
    public R Test(){
        return R.ok("test");
    }
}
