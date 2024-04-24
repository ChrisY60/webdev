package com.example.webdevvideoservice.DTO;

import lombok.Data;

@Data
public class VideoListDTO {
    private Long id;
    private Long userId;
    private String title;
    private String thumbnailUrl;
    private String length;
    private Integer views;
}
