package com.mortal.topicsquare.feign;

import com.mortal.common.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "tibao-searchquestions" , configuration = {FeignRequestInterceptor.class})
public interface saveArticleService {

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String  uploadImage(@RequestPart(name = "file") MultipartFile file);
}
