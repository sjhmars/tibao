package com.mortal.common.utils;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class UploadUtil {

    public static MultipartFile formatFile(MultipartFile file, HttpServletRequest request){
        if (file == null) {
            // 解决服务间调用无法接受MultipartFile参数的问题
            String contentType = request.getContentType();
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
                MultipartHttpServletRequest multipartRequest =
                        WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                if (multipartRequest != null) {
                    MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();
                    for (Map.Entry<String, List<MultipartFile>> entry : multiFileMap.entrySet()) {
                        file = entry.getValue().get(0);
                    }
                }
            }
        }
        return file;
    }
}
