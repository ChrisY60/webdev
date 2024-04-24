package com.example.webdevvideoservice.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class VideoViewDTO {
    @NonNull
    private Long userId;
    @NonNull
    private String title;
    @NonNull
    private String description;
    //@NonNull
    private String url;
    @NonNull
    private String length;
    @NonNull
    private Integer likes;
    @NonNull
    private Integer dislikes;
    @NonNull
    private Integer views;
}
