package com.example.webdevnotificationservice.entity;

import com.example.webdevnotificationservice.enums.NotificationType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long receiverId;
    @NonNull
    private Long senderId;
    @NonNull
    private NotificationType type;
    @Nullable
    private Long videoId;
    @Nullable
    private Long commentId;
    @NonNull
    private String message;
    @NonNull
    private Boolean seen = false;
}
