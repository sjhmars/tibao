package com.mortal.searchquestions.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String Upload(MultipartFile file);
}
