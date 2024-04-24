package com.example.webdevvideoservice.controller;

import com.example.webdevvideoservice.DTO.VideoListDTO;
import com.example.webdevvideoservice.DTO.VideoUploadDTO;
import com.example.webdevvideoservice.DTO.VideoViewDTO;
import com.example.webdevvideoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VideoListDTO>> getVideosByUserId(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(videoService.getVideosByUserId(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoListDTO>> getVideosByTitle(
            @RequestParam String title,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(videoService.getVideosByTitle(title, page, size));
    }

    @PostMapping("/upload")
    public ResponseEntity<VideoUploadDTO> uploadVideo(
        @RequestPart("data") VideoUploadDTO videoUploadDTO,
        @RequestPart("video") MultipartFile videoFile
    ) {
        return ResponseEntity.ok(videoService.uploadVideo(videoFile, videoUploadDTO));
    }

    @PostMapping("/uploadTest")
    public ResponseEntity<VideoUploadDTO> uploadVideoTest(
        @RequestPart("data") VideoUploadDTO videoUploadDTO
    ) {
        return ResponseEntity.ok(videoService.uploadTest(videoUploadDTO));
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoViewDTO> getVideoById(
            @PathVariable Long videoId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(videoService.getVideoById(videoId, userId));
    }

    @PostMapping("/interact")
    public ResponseEntity<Void> likeVideo(
            @RequestParam Long videoId,
            @RequestParam Long userId,
            @RequestParam String interactionType
    ) {
        videoService.interactWithVideo(videoId, userId, interactionType);
        return ResponseEntity.ok().build();
    }
}
