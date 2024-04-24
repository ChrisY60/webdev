package com.example.webdevnotificationservice.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

@Data
public class NotificationDTO {
    @NonNull
    private Long receiverId;
    @NonNull
    private Long senderId;
    @NonNull
    private String type = "VIDEO_UPLOAD";
    @NonNull
    private Long videoId;
    @Nullable
    private Long commentId;
    @NonNull
    private String message;
}
