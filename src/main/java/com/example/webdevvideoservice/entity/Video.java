package com.example.webdevvideoservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String title;
    @Nullable
    private String description;
    @NonNull
    private String url;
    @NonNull
    private String thumbnailUrl;
    @NonNull
    private String length;
    @NonNull
    private Integer likes = 0;
    @NonNull
    private Integer dislikes = 0;
    @NonNull
    private Integer views = 0;
}
