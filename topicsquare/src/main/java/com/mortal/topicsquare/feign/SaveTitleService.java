package com.mortal.topicsquare.feign;

import com.mortal.common.config.FeignRequestInterceptor;
import com.mortal.common.utils.R;
import com.mortal.topicsquare.pojo.QuestionBankPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tibao-questionbank" , configuration = {FeignRequestInterceptor.class})
public interface SaveTitleService {

    @PostMapping("/answer/saveAnswer")
    R saveAnswer(@RequestBody QuestionBankPojo questionBankPojo);

}
