package com.example.webdevvideoservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String storeVideo(MultipartFile file, String blobName);
}
