package com.example.webdevnotificationservice.consumer;

import com.example.webdevnotificationservice.DTO.NotificationDTO;
import com.example.webdevnotificationservice.entity.Notification;
import com.example.webdevnotificationservice.enums.NotificationType;
import com.example.webdevnotificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoEventConsumer {
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "video-upload", groupId = "notification-service")
    public void uploadNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setReceiverId(notificationDTO.getReceiverId());
        notification.setSenderId(notificationDTO.getSenderId());
        notification.setVideoId(notificationDTO.getVideoId());
        notification.setType(NotificationType.valueOf("VIDEO_UPLOAD"));
        notification.setCommentId(notificationDTO.getCommentId());
        notification.setMessage(notificationDTO.getMessage());

        notificationRepository.save(notification);
    }

    @KafkaListener(topics = "video-like", groupId = "notification-service")
    public void likeNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setReceiverId(notificationDTO.getReceiverId());
        notification.setSenderId(notificationDTO.getSenderId());
        notification.setVideoId(notificationDTO.getVideoId());
        notification.setType(NotificationType.valueOf("VIDEO_LIKE"));
        notification.setCommentId(notificationDTO.getCommentId());
        notification.setMessage(notificationDTO.getMessage());

        notificationRepository.save(notification);
    }

}