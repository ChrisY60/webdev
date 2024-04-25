package com.example.webdevnotificationservice.service;

import com.example.webdevnotificationservice.DTO.NotificationDTO;

public interface NotificationService {
    void sendVideoUploadNotification(NotificationDTO notificationDTO);
    void sendVideoLikeNotification(NotificationDTO notificationDTO);
}
