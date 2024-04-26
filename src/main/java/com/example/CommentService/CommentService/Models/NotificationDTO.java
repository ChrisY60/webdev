package com.example.CommentService.CommentService.Models;

import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.UUID;

@Data
public class NotificationDTO {
    @Nullable
    private Long receiverId;
    @NonNull
    private Long senderId;
    @NonNull
    private String type = "COMMENT_UPLOAD";
    @NonNull
    private Long videoId;
    @NonNull
    private UUID commentId;
    @NonNull
    private String message;
}


