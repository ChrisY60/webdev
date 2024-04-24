package com.example.webdevvideoservice.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class VideoUploadDTO {
    @NonNull
    private Long userId;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String length;
}
