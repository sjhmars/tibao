package com.mortal.auth.feign;

import com.mortal.common.config.FeignRequestInterceptor;
import com.mortal.common.utils.R;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "tibao-searchquestions" , configuration = {FeignRequestInterceptor.class})
public interface SaveHeadImg {
    /**
     * 调用远程服务的文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String  uploadImage(@RequestPart(name = "file") MultipartFile file);
}
