package com.mortal.topicsquare.feign;

import com.mortal.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("tibao-questionbank")
public interface SaveTitleService {
    @PostMapping("/Preserve/save")
    public R save();
}
