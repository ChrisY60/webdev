package com.example.webdevnotificationservice.consumer;

import com.example.webdevnotificationservice.DTO.NotificationDTO;
import com.example.webdevnotificationservice.entity.Notification;
import com.example.webdevnotificationservice.repository.NotificationRepository;
import com.example.webdevnotificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.webdevnotificationservice.mapper.NotificationMapper.NOTIFICATION_MAPPER;

@Component
@RequiredArgsConstructor
public class VideoEventConsumer {
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    @KafkaListener(topics = "video-upload", groupId = "notification-service")
    public void uploadNotification(NotificationDTO notificationDTO) {
        notificationService.sendVideoUploadNotification(notificationDTO);
        Notification notification = NOTIFICATION_MAPPER.fromNotificationDTO(notificationDTO);
        notificationRepository.save(notification);
    }

    @KafkaListener(topics = "video-like", groupId = "notification-service")
    public void likeNotification(NotificationDTO notificationDTO) {
        notificationService.sendVideoLikeNotification(notificationDTO);
        Notification notification = NOTIFICATION_MAPPER.fromNotificationDTO(notificationDTO);
        notificationRepository.save(notification);
    }

}