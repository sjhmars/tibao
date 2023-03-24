package com.mortal.searchquestions.controller;

import com.mortal.common.utils.R;
import com.mortal.common.utils.UploadUtil;
import com.mortal.searchquestions.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    public String  uploadImage(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        //file =  UploadUtil.formatFile(file,request);
        List<String> allowTypes = Arrays.asList("image/png","image/jpeg","image/jpg");
        return uploadService.Upload(file);
    }
}
