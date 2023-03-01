package com.mortal.gateway.feign;

import com.mortal.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("tibao-auth")
public interface AuthService {
    //@PostMapping("/login")
    //R Login(@Valid @RequestBody UserLoginVo userLoginVo, BindingResult result);
}
