package com.example.webdevvideoservice.service;

import com.example.webdevvideoservice.DTO.VideoListDTO;
import com.example.webdevvideoservice.DTO.VideoUploadDTO;
import com.example.webdevvideoservice.DTO.VideoViewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    List<VideoListDTO> getVideosByUserId(Long userId);
    List<VideoListDTO> getVideosByTitle(String title, Integer page, Integer size);
    VideoUploadDTO uploadVideo(MultipartFile videoFile, VideoUploadDTO videoUploadDTO);
    VideoUploadDTO uploadTest(VideoUploadDTO videoUploadDTO);
    VideoViewDTO getVideoById(Long videoId, Long userId);
    void interactWithVideo(Long videoId, Long userId, String interactionType);
}
