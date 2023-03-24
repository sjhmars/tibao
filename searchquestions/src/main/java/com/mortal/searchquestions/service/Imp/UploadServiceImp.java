package com.mortal.searchquestions.service.Imp;

import com.mortal.searchquestions.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UploadServiceImp implements UploadService {

    private static final List<String> AllOW_TYPES = Arrays.asList("image/png","image/jpeg","image/jpg");

    @Override
    public String Upload(MultipartFile file) {
        try{
            String contentType = file.getContentType();
            if (!AllOW_TYPES.contains(contentType)){
                return "图片类型错误";
            }
            String filename = file.getOriginalFilename();

            File destFile = new File("E:\\bishe\\images",filename);

            file.transferTo(destFile);
            return filename;
        }catch (Exception e){
            log.error("微服务上传文件失败");
            return "上传失败";
        }
    }
}
